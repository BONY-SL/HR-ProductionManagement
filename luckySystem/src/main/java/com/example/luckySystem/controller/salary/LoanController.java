package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.salary.LoanDto;
import com.example.luckySystem.service.salaryservice.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class LoanController {

    @Autowired
    private LoanService loanService;


    @GetMapping("/getLoan")
    public ResponseEntity<List<LoanDto>> getAllLoanDetails(){
        System.out.println("Received request to save data.");
        List<LoanDto> loandto = loanService.getLoanDetails();
        return ResponseEntity.ok().body(loandto);
    }


    @PostMapping("/addLoan")
    public LoanDto addLoan(@RequestBody LoanDto loanDto) {
        System.out.println("Received request to sav data.");
        return loanService.addLoanDetails(loanDto);
    }

    @PutMapping("/updateLone")
    public ResponseEntity<LoanDto> updateLoneDetails(@RequestBody LoanDto loanDto) {
        LoanDto updatedLoan = loanService.updateLoanDetails(loanDto);
        return ResponseEntity.ok().body(updatedLoan);
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
