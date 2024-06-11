package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.AdvanceDto;
import com.example.luckySystem.dto.salary.AttendanceDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeAdvanceSalary;
import com.example.luckySystem.entity.EmployeeAttendance;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.salary.AttendanceRepo;
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
public class AttendanceService {

    @Autowired
    public AttendanceRepo attendanceRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;

    public List<AttendanceDto> getAttendanceDetails(){
        List<EmployeeAttendance>AttendanceList=attendanceRepo.findAll();
        return AttendanceList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AttendanceDto convertToDTO(EmployeeAttendance unit) {

        return new AttendanceDto(unit.getEmployee_attendance_id(),unit.getEmp_id().getEmployee_id(),unit.getAttendance_status(),unit.getIn_time(),unit.getOut_time(),unit.getDate());
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
