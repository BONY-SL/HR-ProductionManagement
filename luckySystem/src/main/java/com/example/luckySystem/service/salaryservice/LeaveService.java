package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.AttendanceDto;
import com.example.luckySystem.dto.salary.LeaveDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeAttendance;
import com.example.luckySystem.entity.EmployeeLeave;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.LeaveRepo;
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
public class LeaveService {

    @Autowired
    public LeaveRepo leaveRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;



    public List<LeaveDto> getLeaveDetails(){
        List<EmployeeLeave>LeaveList=leaveRepo.findAll();
        return LeaveList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private LeaveDto convertToDTO(EmployeeLeave unit) {

        return new LeaveDto(unit.getEmployee_leave_id(),unit.getEmp_id().getEmployee_id(),unit.getLeave_type(),unit.getReson(),unit.getStatus(),unit.getStart_time(),unit.getEnd_time());
    }




    public LeaveDto addLeave(LeaveDto leaveDto) {
        Employee emp=emprepo.findById(leaveDto.getEmp_id()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        EmployeeLeave leave = modelMapper.map(leaveDto, EmployeeLeave.class);
        leave.setEmp_id(emp);
        leaveRepo.save(leave);
        return leaveDto;
    }

}
