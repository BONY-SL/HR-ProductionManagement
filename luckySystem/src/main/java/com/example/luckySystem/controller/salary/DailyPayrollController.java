package com.example.luckySystem.controller.salary;
import com.example.luckySystem.service.salaryservice.DailyPayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class DailyPayrollController {

    private final DailyPayrollService dailyPayrollService;

    @Autowired
    public DailyPayrollController(DailyPayrollService dailyPayrollService) {
        this.dailyPayrollService = dailyPayrollService;
    }

    @GetMapping("/daily-payroll")
    public void createDailyPayroll(@RequestParam String empId, @RequestParam String date) {
        System.out.println("work");
        System.out.println(empId);
        System.out.println(date);
        dailyPayrollService.createDailyPayroll(empId, date);
    }
}
