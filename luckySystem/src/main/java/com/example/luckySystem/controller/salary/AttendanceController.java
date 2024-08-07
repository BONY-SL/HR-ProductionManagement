package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.employee.*;
import com.example.luckySystem.dto.salary.AttendanceDto;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.service.salaryservice.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/getMedicalByMonthAndYear/{empId}/{month}/{year}")
    public List<MedicalChartDTO> getMedicalByMonthAndYear(@PathVariable String empId, @PathVariable int month, @PathVariable int year) {

        System.out.println(empId);
        System.out.println(month);
        System.out.println(year);

        return attendanceService.getMedicalByMonthAndYear(empId, month, year);
    }

    @GetMapping("/getGatePassByMonthAndYear/{empId}/{month}/{year}")
    public List<GatePassChartDTO> getGatePassByMonthAndYear(@PathVariable String empId, @PathVariable int month, @PathVariable int year) {

        System.out.println(empId);
        System.out.println(month);
        System.out.println(year);

        return attendanceService.getGatePassByMonthAndYear(empId, month, year);
    }

    @GetMapping("/getLeavesByMonthAndYear/{empId}/{year}")
    public List<LeaveChartDTO> getLeavesByMonthAndYear(@PathVariable String empId,@PathVariable int year) {

        System.out.println(empId);
        System.out.println(year);

        return attendanceService.getLeavesByMonthAndYear(empId, year);
    }

    @PutMapping("/updateEmployeePerformance")
    public ResponseEntity<String> updateEmployeePerformance(@RequestBody UpdateEmployeePerformance updateEmployeePerformance){

        System.out.println(updateEmployeePerformance);

        String msg=attendanceService.updateEmployeePerformance(updateEmployeePerformance);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
