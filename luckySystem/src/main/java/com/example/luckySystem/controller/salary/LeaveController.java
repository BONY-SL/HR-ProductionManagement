package com.example.luckySystem.controller.salary;


import com.example.luckySystem.dto.salary.LeaveDto;
import com.example.luckySystem.service.salaryservice.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
