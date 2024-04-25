package com.example.luckySystem.service;

import com.example.luckySystem.dto.AdvanceDto;
import com.example.luckySystem.dto.BasicSalaryDto;
import com.example.luckySystem.dto.LoanDto;
import com.example.luckySystem.entity.BasicSalary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeAdvanceSalary;
import com.example.luckySystem.entity.EmployeeLoan;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.AdvanceRepo;
import com.example.luckySystem.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdvanceService {

    @Autowired
    public AdvanceRepo advanceRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;

    public List<AdvanceDto> getAdvance() {
        List<EmployeeAdvanceSalary> AdvanceList = advanceRepo.findAll();
        return modelMapper.map(AdvanceList, new TypeToken<List<AdvanceDto>>() {}.getType());
    }

    public AdvanceDto addAdvanceDetails(AdvanceDto advanceDto) {

        Employee emp=emprepo.findById(advanceDto.getEmp_id()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

       EmployeeAdvanceSalary advance = modelMapper.map(advanceDto, EmployeeAdvanceSalary.class);
        advance.setEmp_id(emp);
        advanceRepo.save(advance);
        return advanceDto;
    }

}
