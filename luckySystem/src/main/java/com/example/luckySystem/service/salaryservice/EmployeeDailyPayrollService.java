package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.entity.DailyPayRoll;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeDailyPayRoll;
import com.example.luckySystem.repo.salary.DailyPayrollRepo;
import com.example.luckySystem.repo.salary.EmployeeDailyPayrollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class EmployeeDailyPayrollService {

    private final EmployeeDailyPayrollRepo employeeDailyPayrollRepo;
    private final DailyPayrollRepo dailyPayrollRepo; // Inject DailyPayrollRepo

    @Autowired
    public EmployeeDailyPayrollService(EmployeeDailyPayrollRepo employeeDailyPayrollRepo, DailyPayrollRepo dailyPayrollRepo) {
        this.employeeDailyPayrollRepo = employeeDailyPayrollRepo;
        this.dailyPayrollRepo = dailyPayrollRepo;
    }

    public void createEmployeeDailyPayroll(DailyPayRoll savedDailyPayRoll, Employee empid, String date) {
        System.out.println("Creating EmployeeDailyPayroll based on DailyPayRoll object:");
        System.out.println(savedDailyPayRoll);

        double otamount = 0;

        if (savedDailyPayRoll.getWorking_hours() > 8) {
            otamount = savedDailyPayRoll.getAmount_per_aditonal_hour() * (savedDailyPayRoll.getWorking_hours() - 8);
            double othours=savedDailyPayRoll.getWorking_hours()-8;
            System.out.println("ot hours:"+othours);
            System.out.println("ot amount:" +otamount);
        }


        // Create EmployeeDailyPayRoll object and set properties
        EmployeeDailyPayRoll employeeDailyPayRoll = new EmployeeDailyPayRoll();
        employeeDailyPayRoll.setDaily_pay_id(savedDailyPayRoll);
        employeeDailyPayRoll.setShift_amount(savedDailyPayRoll.getShift_amount());
        employeeDailyPayRoll.setWorking_hours(savedDailyPayRoll.getWorking_hours());
        employeeDailyPayRoll.setEmp_id(empid);
        employeeDailyPayRoll.setOt_amount(otamount);
        employeeDailyPayRoll.setTotal_amount(savedDailyPayRoll.getShift_amount() + otamount);
        employeeDailyPayRoll.setDate(Date.valueOf(date));

        // Save EmployeeDailyPayRoll object
       employeeDailyPayrollRepo.save(employeeDailyPayRoll);

        System.out.println(employeeDailyPayRoll);


    }


}
