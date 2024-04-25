package com.example.luckySystem.controller;


import com.example.luckySystem.dto.BasicSalaryDto;
import com.example.luckySystem.dto.LoanDto;
import com.example.luckySystem.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/getLoan")
    public List<LoanDto> getAllLoanDetails(){
        System.out.println("Received request to save allowance data.");
        return loanService.getLoanDetails();
    }

    @PostMapping("/addLoan")
    public LoanDto addLoan(@RequestBody LoanDto loanDto) {
        System.out.println("Received request to sav data.");
        return loanService.addLoanDetails(loanDto);
    }

    @PutMapping("/updateLone")
    public LoanDto updateLoneDetails(@RequestBody LoanDto loanDto) {
        return loanService.updatelone(loanDto);
    }

}
