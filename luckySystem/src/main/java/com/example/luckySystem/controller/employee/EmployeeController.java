
package com.example.luckySystem.controller.employee;

import com.example.luckySystem.dto.employee.EmployeeDTO;
import com.example.luckySystem.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getEmployee")
    public List<EmployeeDTO> getAllEmployeeDetails() {
        System.out.println("Received request to get all employees.");
        return employeeService.getEmployee();
    }

    @PostMapping("/addEmployee")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDto) {
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
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDto) {
        return employeeService.updateEmployeeDetails(employeeDto);
    }
    @DeleteMapping("/deleteEmployee")
    public boolean deleteEmployee(@RequestBody EmployeeDTO employeeDto) {
        return employeeService.deleteEmployee(employeeDto);
    }

    @GetMapping("/getEmployeeByID/{employeeID}")
    public EmployeeDTO getEmployeeById(@PathVariable String employeeID){
        return employeeService.getEmployeeByEmployeeID(employeeID);
    }

}
