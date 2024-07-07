package com.example.luckySystem.controller.salary;
import com.example.luckySystem.service.salaryservice.EmployeeDailyPayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class EmployeeDailyPayrollController {

    private final EmployeeDailyPayrollService employeeDailyPayrollService;

    @Autowired
    public EmployeeDailyPayrollController(EmployeeDailyPayrollService employeeDailyPayrollService) {
        this.employeeDailyPayrollService = employeeDailyPayrollService;
    }

  /*  @PostMapping("/employeedailypayroll")
    public void createEmployeeDailyPayroll(@RequestParam String empId, @RequestParam String date){
        System.out.println("work");
        employeeDailyPayrollService.createEmployeeDailyPayroll(empId, date);
    }

   */

    @PostMapping("/employeedaily-payroll")
    public void createEmployeeDailyPayroll(@RequestParam String empId, @RequestParam String date) {
        System.out.println("work");
        //employeeDailyPayrollService.createEmployeeDailyPayroll(empId,date);

    }
}
