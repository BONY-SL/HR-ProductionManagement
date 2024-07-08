package com.example.luckySystem.controller.employee;
import com.example.luckySystem.dto.employee.WorkingAndAbsentEmployeeDetails;
import com.example.luckySystem.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
@RequiredArgsConstructor
public class HRDashBoardConroller {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/WorkingAndAbsentEmployeeDetails")
    public List<WorkingAndAbsentEmployeeDetails> WorkingAndAbsentEmployeeDetails() {

        List<WorkingAndAbsentEmployeeDetails> employeeDetails = employeeService.WorkingAndAbsentEmployeeDetails();

        System.out.println(employeeDetails);

        return employeeDetails;
    }
}
