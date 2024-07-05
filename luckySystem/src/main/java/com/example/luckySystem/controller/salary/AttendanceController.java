package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.employee.AttendanceChartDTO;
import com.example.luckySystem.dto.salary.AttendanceDto;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.service.salaryservice.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class AttendanceController {

    @Autowired
    public AttendanceService attendanceService;

    @GetMapping("/getAttendance")
    public ResponseEntity<List<AttendanceDto>> getAttendanceDetails(){
        System.out.println("Received request to save data.");
        List<AttendanceDto> attendanceDtos = attendanceService.getAttendanceDetails();
        return ResponseEntity.ok().body(attendanceDtos);
    }

    @PostMapping("/addAttendance")
    public AttendanceDto addAttendance(@RequestBody AttendanceDto attendanceDto) throws AppException {
        System.out.println("Received request to save user salary data.");
        return attendanceService.addAttendance(attendanceDto);
    }

    @GetMapping("/employeeCount")
    public int getEmployeeCountByDate(@RequestParam String date) {
        System.out.println("Received request to retrieve employee count by date.");
        return attendanceService.countDistinctEmployeesByDate(date);
    }

    @GetMapping("/getAttendanceByMonthAndYear/{empId}/{month}/{year}")
    public List<AttendanceChartDTO> getAttendanceByMonthAndYear(@PathVariable String empId, @PathVariable int month, @PathVariable int year) {

        System.out.println(empId);
        System.out.println(month);
        System.out.println(year);

        return attendanceService.getAttendanceByMonthAndYear(empId, month, year);
    }


}
