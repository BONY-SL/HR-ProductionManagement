package com.example.luckySystem.controller.salary;

import com.example.luckySystem.service.salaryservice.MonthlySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class MonthlySalaryController {

    @Autowired
    public MonthlySalaryService monthlySalaryService;

    @PostMapping("/monthltysalary")
    public void createMonthlySalary(
            @RequestParam String empId,
            @RequestParam String date,
            @RequestParam(required = false) Double bonus // Use Double to allow null values
    ) {
        System.out.println("Received request for empId: " + empId + ", date: " + date + ", bonus: " + bonus);

        System.out.println(bonus);

        // Check if bonus is null or not provided
        if (bonus == null) {
            bonus = 0.0; // Set a default value or handle accordingly
        }

        monthlySalaryService.createMonthlySalary(empId, date, bonus);
    }
}
