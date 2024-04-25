package com.example.luckySystem.controller;


import com.example.luckySystem.dto.AllowanceDto;
import com.example.luckySystem.service.AllowanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
public class AllowanceController {

    @Autowired
    private AllowanceService allowanceService;


    @PostMapping("/addAllowance")
    public AllowanceDto addAllowance(@RequestBody AllowanceDto allowanceDto) {
        System.out.println("Received request to save allowance data.");
        return allowanceService.addAllowanceDetails(allowanceDto);
    }

    @GetMapping("/getAllowance")
    public List<AllowanceDto> getAllAllowanceDetails() {
        return allowanceService.getAllowanceDetails();

    }

    @PutMapping("/updateAllowance")
    public AllowanceDto updateAllowanceDetails(@RequestBody AllowanceDto allowanceDto) {
       return allowanceService.updateAllowanceDetails(allowanceDto);

    }
    @DeleteMapping("/deleteAllowance")
    public boolean deleteDeduction(@RequestBody AllowanceDto allowanceDto) {
        return allowanceService.deleteAllowanceDetails(allowanceDto);
    }


    @GetMapping("/getAllowanceByID/{allowanceID}")
    public AllowanceDto getAllowanceId(@PathVariable String allowanceID) {
        return allowanceService.getAllowancesById(allowanceID);
    }


}
