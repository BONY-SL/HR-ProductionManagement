package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.employee.EmployeeDTO;
import com.example.luckySystem.dto.salary.AdvanceDto;
import com.example.luckySystem.dto.salary.LoanDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeAdvanceSalary;
import com.example.luckySystem.entity.EmployeeLoan;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.salary.AdvanceRepo;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdvanceService {

    @Autowired
    public AdvanceRepo advanceRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;

    public List<AdvanceDto> getAdvance(){
        List<EmployeeAdvanceSalary>employeeList=advanceRepo.findAll();
        return employeeList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AdvanceDto convertToDTO(EmployeeAdvanceSalary unit) {

        return new AdvanceDto(unit.getAdvance_salary_id(),unit.getEmp_id().getEmployee_id(),unit.getReson(),unit.getStatus(),unit.getAmount());
    }

    public AdvanceDto addAdvanceDetails(AdvanceDto advanceDto) {

        Employee emp=emprepo.findById(advanceDto.getEmp_id()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

       EmployeeAdvanceSalary advance = modelMapper.map(advanceDto, EmployeeAdvanceSalary.class);
        advance.setEmp_id(emp);
        advanceRepo.save(advance);
        return advanceDto;
    }

    public AdvanceDto updateAdvanceDetails(AdvanceDto advanceDto) {
        EmployeeAdvanceSalary advanceSalary = modelMapper.map(advanceDto, EmployeeAdvanceSalary.class);
        advanceRepo.save(advanceSalary);
        return advanceDto;
    }

    public boolean deleteAdvanceDetails(AdvanceDto advanceDto){
        advanceRepo.delete(modelMapper.map(advanceDto,EmployeeAdvanceSalary.class));
        return true;
    }



    public AdvanceDto getAdvanceByID(String advance_id){

        EmployeeAdvanceSalary advanceSalary = advanceRepo.getAdvanceByID(advance_id);
        if(advanceSalary!=null) {
            AdvanceDto advanceDto = new AdvanceDto();
            advanceDto.setAdvance_salary_id(advanceSalary.getAdvance_salary_id());
            advanceDto.setEmp_id(advanceSalary.getEmp_id().getEmployee_id());
            advanceDto.setReson(advanceSalary.getReson());
            advanceDto.setStatus(advanceSalary.getStatus());
            advanceDto.setStatus(advanceSalary.getStatus());
            return advanceDto;
        } else{
                return null;
            }


        }



}
