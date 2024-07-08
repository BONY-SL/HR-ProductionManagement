package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.salary.DailyPayrollDto;
import com.example.luckySystem.dto.salary.EmployeeDailyPayrollDto;
import com.example.luckySystem.dto.salary.MonthlySalaryDto;
import com.example.luckySystem.service.salaryservice.EmployeeDailyPayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class EmployeeDailyPayrollController {

    private final EmployeeDailyPayrollService employeeDailyPayrollService;

    @Autowired
    public EmployeeDailyPayrollController(EmployeeDailyPayrollService employeeDailyPayrollService) {
        this.employeeDailyPayrollService = employeeDailyPayrollService;
    }


    @PostMapping("/employeedaily-payroll")
    public void createEmployeeDailyPayroll(@RequestParam String empId, @RequestParam String date) {
        System.out.println("work");
        //employeeDailyPayrollService.createEmployeeDailyPayroll(empId,date);
    }



    @GetMapping("/geemployeedailypayroll")
     public List<EmployeeDailyPayrollDto> getEmployeeDailyPayroll(){
            System.out.println("Received request to fetch daily payroll data.");
            List<EmployeeDailyPayrollDto>employeeDailyPayrollDto=employeeDailyPayrollService.getEmployeeDailypayroll();
            return employeeDailyPayrollDto;


        }

    }





