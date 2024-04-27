package com.example.luckySystem.controller.salary;

import com.example.luckySystem.dto.salary.AttendanceDto;
import com.example.luckySystem.service.salaryservice.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
public class AttendanceController {

    @Autowired
    public AttendanceService attendanceService;

    @GetMapping("/getAttendance")
    public List<AttendanceDto> getAttendanceDetails(){
        System.out.println("Received request to save data.");
        return attendanceService.getAttendanceDetails();
    }

    @PostMapping("/addAttendance")
    public AttendanceDto addAttendance(@RequestBody AttendanceDto attendanceDto) {
        System.out.println("Received request to save user salary data.");
        return attendanceService.addAttendance(attendanceDto);
    }

    @GetMapping("/employeeCount")
    public int getEmployeeCountByDate(@RequestParam String date) {
        System.out.println("Received request to retrieve employee count by date.");
        return attendanceService.countDistinctEmployeesByDate(date);
    }



}
