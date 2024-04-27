package com.example.luckySystem.controller.salary;

import com.example.luckySystem.dto.salary.GatePassDto;
import com.example.luckySystem.service.salaryservice.GatepassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")

public class GatePassController {

    @Autowired
    public GatepassService gatepassService;

    @PostMapping("/addGatepass")
    public GatePassDto addGatepass(@RequestBody GatePassDto gatePassDto) {
        System.out.println("Received request to save user data.");
        return gatepassService.addGatepass(gatePassDto);
    }
    @GetMapping("/getGatepass")
    public List<GatePassDto> getGatepassDetails() {
        return gatepassService.getGatepassDetails();
    }



}
