package com.example.luckySystem.service.Issue;
import com.example.luckySystem.dto.issue.DailyIssuEmployeeDTO;
import com.example.luckySystem.dto.issue.GetMonthlyIssueDTO;
import com.example.luckySystem.dto.issue.IssueDTO;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.issue.DailyIssueRepo;
import com.example.luckySystem.repo.issue.IssueRepo;
import com.example.luckySystem.service.employee.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IssueRepo issueRepo;

    @Autowired
    private DailyIssueRepo dailyIssueRepo;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepo employeeRepo;


    public ProductionIssue addNewIssue(String  issue) {
        ProductionIssue entity = new ProductionIssue();
        entity.setIssue_name(issue);
        return issueRepo.save(entity);
    }

    public List<IssueDTO> getIssueDetails() {
        List<ProductionIssue> issues= issueRepo.findAll();
        return issues.stream().map(this::convertIssueEntityToDTO).collect(Collectors.toList());
    }

    private IssueDTO convertIssueEntityToDTO(ProductionIssue unit) {

        return new IssueDTO(unit.getIssue_id(),unit.getIssue_name());
    }

    public void updateIssue(IssueDTO dto) {

        ProductionIssue unit = issueRepo.findById(dto.getIssue_id()).orElseThrow();
        unit.setIssue_id(dto.getIssue_id());
        unit.setIssue_name(dto.getIssue_name());
        issueRepo.save(unit);
    }

    public DailyProductionIssuesByEmployee addDailyIssues(DailyIssuEmployeeDTO dto){
        //System.out.println("service 1"+dto);
        if(!employeeService.employeeExists(dto.getEmp_id())) {
            throw new AppException("Invalid Employee ID: " + dto.getEmp_id(), HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeRepo.findById(dto.getEmp_id())
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));

        DailyProductionIssuesByEmployee entity = modelMapper.map(dto, DailyProductionIssuesByEmployee.class);
        entity.setEmp_id(employee);
        return dailyIssueRepo.save(entity);
    }

    public List<DailyIssuEmployeeDTO> gettAllIssueByEmployee() {

        List<DailyProductionIssuesByEmployee> units = dailyIssueRepo.findAll();
        return units.stream().map(this::IssueconvertEntityToDTO).collect(Collectors.toList());
    }

    private DailyIssuEmployeeDTO IssueconvertEntityToDTO(DailyProductionIssuesByEmployee unit) {

        return new DailyIssuEmployeeDTO(unit.getDaily_issue_id(),unit.getEmp_id().getEmployee_id(),unit.getIssue_name(),unit.getDamage_amount(),unit.getSubmit_date());
    }

    public List<GetMonthlyIssueDTO> getMonthlyIssues(int month, int year) {
        return dailyIssueRepo.findIssuesGroupedByIssueName(month, year);
    }



}
