package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.salary.BasicSalaryDto;
import com.example.luckySystem.service.salaryservice.BasicSalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class BasicSalaryController {
    @Autowired
    private BasicSalaryService basicSalaryService;


    @GetMapping("/getSalary")
    public List<BasicSalaryDto> getSalary() {
        return basicSalaryService.getSalaryDetails();
    }

    @PostMapping("/addSalary")
    public BasicSalaryDto addSalary(@RequestBody BasicSalaryDto basicSalaryDto) {
        System.out.println("Received request to save user salary data.");
        return basicSalaryService.addSalaryDetails(basicSalaryDto);
    }

    @PutMapping("/updateSalary")
    public BasicSalaryDto updateSalary(@RequestBody BasicSalaryDto basicSalaryDto) {
        return basicSalaryService.updateSalaryDetails(basicSalaryDto);
    }

    @DeleteMapping("/deleteSalary")
    public boolean deleteSalary(@RequestBody BasicSalaryDto basicSalaryDto) {
        return basicSalaryService.deleteSalaryDetails(basicSalaryDto);
    }

    @GetMapping("/getSalaryByID/{salaryID}")
    public BasicSalaryDto getSalaryById(@PathVariable String salaryID) {
        return basicSalaryService.getSalaryDetailsByID(salaryID);
    }

}
