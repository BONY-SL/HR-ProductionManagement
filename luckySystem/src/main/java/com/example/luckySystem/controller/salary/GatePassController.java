package com.example.luckySystem.controller.salary;

import com.example.luckySystem.dto.salary.AdvanceDto;
import com.example.luckySystem.dto.salary.GatePassDto;
import com.example.luckySystem.service.salaryservice.GatepassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")

public class GatePassController {

    @Autowired
    public GatepassService gatepassService;

    @PostMapping("/addGatepass")
    public GatePassDto addGatepass(@RequestBody GatePassDto gatePassDto) {
        System.out.println("Received request to save user data.");
        return gatepassService.addGatepass(gatePassDto);
    }



    @GetMapping("/getGatepass")
    public ResponseEntity<List<GatePassDto>> getGatepassDetails() {
        System.out.println("Received request to save data.");
        List<GatePassDto> gatePassDtos = gatepassService.getGatepassDetails();
        return ResponseEntity.ok().body(gatePassDtos);
    }





}
