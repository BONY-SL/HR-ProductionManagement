/*package com.example.luckySystem.controller;


import com.example.luckySystem.dto.salary.DeductionDto;
import com.example.luckySystem.dto.salary.EmployeeDto;
import com.example.luckySystem.dto.salary.LoanDto;
import com.example.luckySystem.service.salaryservice.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")


public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/getEmployee")
    public List<EmployeeDto> getAllEmployeeDetails(){
        System.out.println("Received request to save  data.");
        return employeeService.getEmployee();
    }

    @PostMapping("/addEmployee")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println("Received request to save  data.");
        return employeeService.addEmployee(employeeDto);
    }

    @GetMapping("/employeeCountByDepartment")
    public List<Object[]> getEmployeeCountByDepartment() {
        return employeeService.countActiveEmployeesByDepartment();
    }

    @GetMapping("/totalCount")
    public EmployeeDto getTotalEmployeeCount() {
        int totalCount = employeeService.getTotalEmployeesCount();
        return totalCount;
    }

}
*/

package com.example.luckySystem.controller.salary;

import com.example.luckySystem.dto.salary.EmployeeDto;
import com.example.luckySystem.service.salaryservice.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getEmployee")
    public List<EmployeeDto> getAllEmployeeDetails() {
        System.out.println("Received request to get all employees.");
        return employeeService.getEmployee();
    }

    @PostMapping("/addEmployee")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println("Received request to add an employee.");
        return employeeService.addEmployee(employeeDto);
    }

    @GetMapping("/employeeCountByDepartment")
    public List<Object[]> getEmployeeCountByDepartment() {
        return employeeService.countActiveEmployeesByDepartment();
    }

    @GetMapping("/totalCount")
    public int getTotalEmployeeCount() {
        return employeeService.getTotalEmployeesCount();
    }


    @PutMapping("/updateEmployee")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployeeDetails(employeeDto);
    }
    @DeleteMapping("/deleteEmployee")
    public boolean deleteEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.deleteEmployee(employeeDto);
    }

    @GetMapping("/getEmployeeByID/{employeeID}")
    public EmployeeDto getEmployeeById(@PathVariable String employeeID){
        return employeeService.getEmployeeByEmployeeID(employeeID);
    }

}
