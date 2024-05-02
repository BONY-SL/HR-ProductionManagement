package com.example.luckySystem.service.Issue;


import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.bottles.DamageBottleDTO;
import com.example.luckySystem.dto.issue.DailyIssuEmployeeDTO;
import com.example.luckySystem.dto.issue.IssueDTO;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.agent.AgentRepo;
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
        if(!employeeService.employeeExists(dto.getEmp_id().getEmployee_id())) {
            throw new AppException("Invalid Employee ID: " + dto.getEmp_id().getEmployee_id(), HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeRepo.findById(dto.getEmp_id().getEmployee_id())
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));

        DailyProductionIssuesByEmployee entity = modelMapper.map(dto, DailyProductionIssuesByEmployee.class);
        entity.setEmp_id(employee);
        return dailyIssueRepo.save(entity);
    }
}
