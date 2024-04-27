package com.example.luckySystem.controller.salary;


import com.example.luckySystem.dto.salary.LoanDto;
import com.example.luckySystem.service.salaryservice.LoanService;
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


    @DeleteMapping("/deleteLoan")
    public boolean deleteLoan(@RequestBody LoanDto loanDto) {
        return loanService.deleteLoanDetails(loanDto);
    }



    @GetMapping("/getLoanByID/{loanID}")
    public LoanDto getLoanById(@PathVariable String loanID) {
        System.out.println(loanID);
        return loanService.getLoanDetailsByID(loanID);
    }



}
