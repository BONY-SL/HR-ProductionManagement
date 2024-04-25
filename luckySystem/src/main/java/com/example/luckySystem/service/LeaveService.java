package com.example.luckySystem.service;

import com.example.luckySystem.dto.BasicSalaryDto;
import com.example.luckySystem.dto.LeaveDto;
import com.example.luckySystem.dto.LoanDto;
import com.example.luckySystem.entity.BasicSalary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLeave;
import com.example.luckySystem.entity.EmployeeLoan;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.EmployeeRepo;
import com.example.luckySystem.repo.LeaveRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class LeaveService {

    @Autowired
    public LeaveRepo leaveRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;
    public List<LeaveDto> getLeaveDetails() {
        List<EmployeeLeave> leaveListList = leaveRepo.findAll();
        return modelMapper.map(leaveListList, new TypeToken<List<LeaveDto>>() {}.getType());
    }

    public LeaveDto addLeave(LeaveDto leaveDto) {
        Employee emp=emprepo.findById(leaveDto.getEmp_id()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        EmployeeLeave leave = modelMapper.map(leaveDto, EmployeeLeave.class);
        leave.setEmp_id(emp);
        leaveRepo.save(leave);
        return leaveDto;
    }

}
