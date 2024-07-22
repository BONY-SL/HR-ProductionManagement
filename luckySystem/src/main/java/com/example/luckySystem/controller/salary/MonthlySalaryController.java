package com.example.luckySystem.controller.salary;

import com.example.luckySystem.dto.salary.MonthlySalaryDto;
import com.example.luckySystem.dto.salary.MonthlySalaryReportDto;
import com.example.luckySystem.dto.sectionanddepartment.SectionDto;
import com.example.luckySystem.service.salaryservice.MonthlySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/getMonthlySalary")
    public ResponseEntity<List<MonthlySalaryDto>> getMonthlyDetails() {
        System.out.println("Received request to save data.");
        List<MonthlySalaryDto> monthlySalaryDtos =monthlySalaryService.MonthlySalaryDetails();
        return ResponseEntity.ok().body(monthlySalaryDtos);
    }




    @GetMapping("/monthltysalaryReport")
    public MonthlySalaryReportDto createMonthlySalaryReport(
            @RequestParam String empId,
            @RequestParam String date

    ) {
        System.out.println("Received request for empId: " + empId + ", date: " + date);


           MonthlySalaryReportDto monthlySalaryReportDtos= monthlySalaryService.getMonthlySalaryReport(empId, date);
           return monthlySalaryReportDtos;
    }



    @GetMapping("/epfReport")
    public ResponseEntity<List<MonthlySalaryReportDto>> EPFReport(@RequestParam String date) {
        System.out.println("Received request for empId: date: " + date);

        List<MonthlySalaryReportDto>monthlySalaryReportDtoList=monthlySalaryService.EPFReport(date);
        return ResponseEntity.ok().body(monthlySalaryReportDtoList);
    }




}
