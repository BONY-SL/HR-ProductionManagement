package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.AdvanceDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeAdvanceSalary;
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
        return modelMapper.map(advanceSalary, AdvanceDto.class);
    }

}
