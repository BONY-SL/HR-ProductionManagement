package com.example.luckySystem.controller.salary;


import com.example.luckySystem.dto.salary.AttendanceDto;
import com.example.luckySystem.dto.salary.LeaveDto;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.service.salaryservice.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")

public class LeaveController {
    @Autowired
    public LeaveService leaveService;

    @GetMapping("/getLeave")
    public ResponseEntity<List<LeaveDto>> getLeaveDetails(){
        System.out.println("Received request to save data.");
        List<LeaveDto> leaveDtos = leaveService.getLeaveDetails();
        return ResponseEntity.ok().body(leaveDtos);
    }


    @PostMapping("/addLeave")
    public LeaveDto addLeave(@RequestBody LeaveDto leaveDto) throws AppException {
        System.out.println("Received request to save user salary data.");
        return leaveService.addLeave(leaveDto);
    }


}
