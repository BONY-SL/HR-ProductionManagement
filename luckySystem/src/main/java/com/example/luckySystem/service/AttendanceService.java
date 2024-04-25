package com.example.luckySystem.service;

import com.example.luckySystem.dto.AttendanceDto;
import com.example.luckySystem.dto.BasicSalaryDto;
import com.example.luckySystem.dto.LoanDto;
import com.example.luckySystem.entity.BasicSalary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeAttendance;
import com.example.luckySystem.entity.EmployeeLoan;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.AttendanceRepo;
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
public class AttendanceService {

    @Autowired
    public AttendanceRepo attendanceRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;



    public List<AttendanceDto> getAttendanceDetails() {
        List<EmployeeAttendance> AttendanceList = attendanceRepo.findAll();
        return modelMapper.map(AttendanceList, new TypeToken<List<AttendanceDto>>() {}.getType());
    }

    public AttendanceDto addAttendance(AttendanceDto attendanceDto) {
        Employee emp=emprepo.findById(String.valueOf(attendanceDto.getEmp_id())).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));


        EmployeeAttendance attendance = modelMapper.map(attendanceDto, EmployeeAttendance.class);
        attendance.setEmp_id(emp);
        attendanceRepo.save(attendance);
        return attendanceDto;
    }

    public int countDistinctEmployeesByDate(String date) {
        return attendanceRepo.countDistinctEmployeesByDate(date);
    }


}
