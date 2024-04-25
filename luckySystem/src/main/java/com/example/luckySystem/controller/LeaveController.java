package com.example.luckySystem.controller;


import com.example.luckySystem.dto.BasicSalaryDto;
import com.example.luckySystem.dto.LeaveDto;
import com.example.luckySystem.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")

public class LeaveController {
    @Autowired
    public LeaveService leaveService;

    @GetMapping("/getLeave")
    public List<LeaveDto> getLeaveDetails(){
        System.out.println("Received request to save data.");
        return leaveService.getLeaveDetails();
    }

    @PostMapping("/addLeave")
    public LeaveDto addLeave(@RequestBody LeaveDto leaveDto) {
        System.out.println("Received request to save user salary data.");
        return leaveService.addLeave(leaveDto);
    }


}
