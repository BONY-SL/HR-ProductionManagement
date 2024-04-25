package com.example.luckySystem.service;


import com.example.luckySystem.dto.BasicSalaryDto;
import com.example.luckySystem.dto.GatePassDto;
import com.example.luckySystem.dto.LeaveDto;
import com.example.luckySystem.entity.BasicSalary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeGatePass;
import com.example.luckySystem.entity.EmployeeLeave;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.EmployeeRepo;
import com.example.luckySystem.repo.GatePassRepo;
import jdk.swing.interop.SwingInterOpUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GatepassService {

    @Autowired
    public GatePassRepo gatePassRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;

    public GatePassDto addGatepass(GatePassDto gatePassDto) {

        Employee emp=emprepo.findById(gatePassDto.getEmp_id()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        EmployeeGatePass gatePass = modelMapper.map(gatePassDto, EmployeeGatePass.class);
       gatePass.setEmp_id(emp);
        gatePassRepo.save(gatePass);
        return gatePassDto;
    }

    public List<GatePassDto> getGatepassDetails() {
        List<EmployeeGatePass> gatepassList = gatePassRepo.findAll();
        return modelMapper.map(gatepassList, new TypeToken<List<GatePassDto>>() {}.getType());
    }


}
