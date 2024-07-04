package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.entity.DailyPayRoll;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeDailyPayRoll;
import com.example.luckySystem.repo.salary.DailyPayrollRepo;
import com.example.luckySystem.repo.salary.EmployeeDailyPayrollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDailyPayrollService {

    private final EmployeeDailyPayrollRepo employeeDailyPayrollRepo;
    private final DailyPayrollRepo dailyPayrollRepo; // Inject DailyPayrollRepo

    @Autowired
    public EmployeeDailyPayrollService(EmployeeDailyPayrollRepo employeeDailyPayrollRepo, DailyPayrollRepo dailyPayrollRepo) {
        this.employeeDailyPayrollRepo = employeeDailyPayrollRepo;
        this.dailyPayrollRepo = dailyPayrollRepo;
    }

    public void createEmployeeDailyPayroll(DailyPayRoll savedDailyPayRoll, Employee empid) {
        System.out.println("Creating EmployeeDailyPayroll based on DailyPayRoll object:");
        System.out.println(savedDailyPayRoll);

        // Fetch DailyPayRoll entity using its ID
        DailyPayRoll dailyPayRoll = dailyPayrollRepo.findById(savedDailyPayRoll.getDaily_pay_roll_id()).orElse(null);
        if (dailyPayRoll != null) {
            double otamount = 0;

            if (savedDailyPayRoll.getWorking_hours() > 8) {
                otamount = savedDailyPayRoll.getAmount_per_aditonal_hour() * (savedDailyPayRoll.getWorking_hours() - 8);
            }

            // Create EmployeeDailyPayRoll object and set DailyPayRoll
            EmployeeDailyPayRoll employeeDailyPayRoll = new EmployeeDailyPayRoll();
            employeeDailyPayRoll.setDaily_pay_id(dailyPayRoll);
            employeeDailyPayRoll.setShift_amount(savedDailyPayRoll.getShift_amount());
            employeeDailyPayRoll.setWorking_hours(savedDailyPayRoll.getWorking_hours());
            employeeDailyPayRoll.setEmp_id(empid);
            employeeDailyPayRoll.setOt_amount(otamount);
            employeeDailyPayRoll.setTotal_amount(savedDailyPayRoll.getShift_amount() + otamount);

            // Save EmployeeDailyPayRoll object
            employeeDailyPayrollRepo.save(employeeDailyPayRoll);

            System.out.println(employeeDailyPayRoll);
        } else {
            System.out.println("DailyPayRoll with ID " + savedDailyPayRoll.getDaily_pay_roll_id() + " not found.");
        }
    }
}

