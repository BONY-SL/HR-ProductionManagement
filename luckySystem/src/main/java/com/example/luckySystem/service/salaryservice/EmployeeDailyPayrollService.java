package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.salary.DailyPayrollDto;
import com.example.luckySystem.dto.salary.EmployeeDailyPayrollDto;
import com.example.luckySystem.dto.salary.MonthlySalaryDto;
import com.example.luckySystem.entity.DailyPayRoll;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeDailyPayRoll;
import com.example.luckySystem.entity.EmployeeMonthlySalary;
import com.example.luckySystem.repo.salary.BasicSalaryRepo;
import com.example.luckySystem.repo.salary.DailyPayrollRepo;
import com.example.luckySystem.repo.salary.EmployeeDailyPayrollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDailyPayrollService {

    private final EmployeeDailyPayrollRepo employeeDailyPayrollRepo;

    private final DailyPayrollRepo dailyPayrollRepo; // Inject DailyPayrollRepo



    private double totamount;
    private double othours;

    @Autowired
    public EmployeeDailyPayrollService(EmployeeDailyPayrollRepo employeeDailyPayrollRepo, DailyPayrollRepo dailyPayrollRepo) {
        this.employeeDailyPayrollRepo = employeeDailyPayrollRepo;
        this.dailyPayrollRepo = dailyPayrollRepo;
    }

    public void createEmployeeDailyPayroll(DailyPayRoll savedDailyPayRoll, Employee empid, String dateStr,double lateAmount,double gatepassAmount ) {
        System.out.println("Creating EmployeeDailyPayroll based on DailyPayRoll object:");
        System.out.println(savedDailyPayRoll);


        // Parse the date string to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // Check if the date is a Saturday or Sunday
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            System.out.println("The date " + dateStr + " is a Saturday.");
            if(savedDailyPayRoll.getWorking_hours()>4){
                double othours=savedDailyPayRoll.getWorking_hours()-4;
                totamount=othours*savedDailyPayRoll.getAmount_per_aditonal_hour();
            }else {
                totamount=0.0;
            }

        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            System.out.println("The date " + dateStr + " is a Sunday.");
            //double ot
            totamount=savedDailyPayRoll.getWorking_hours()*(savedDailyPayRoll.getAmount_per_aditonal_hour()*2);

        }else if(savedDailyPayRoll.getWorking_hours()>8){

            othours=savedDailyPayRoll.getWorking_hours()-8;
            totamount=othours*savedDailyPayRoll.getAmount_per_aditonal_hour();
        }else {
            totamount=0.0;
        }


        System.out.println("total ot amount:" +totamount);



        // Create EmployeeDailyPayRoll object and set properties
        EmployeeDailyPayRoll employeeDailyPayRoll = new EmployeeDailyPayRoll();
        employeeDailyPayRoll.setDaily_pay_id(savedDailyPayRoll);
        employeeDailyPayRoll.setShift_amount(savedDailyPayRoll.getShift_amount());
        employeeDailyPayRoll.setWorking_hours(savedDailyPayRoll.getWorking_hours());
        employeeDailyPayRoll.setEmp_id(empid);
        employeeDailyPayRoll.setOt_amount(totamount);
        employeeDailyPayRoll.setGatepass_amount(gatepassAmount);
        employeeDailyPayRoll.setLate_amount(lateAmount);
        employeeDailyPayRoll.setTotal_amount(savedDailyPayRoll.getShift_amount() + totamount);
        employeeDailyPayRoll.setDate(Date.valueOf(dateStr));

        // Save EmployeeDailyPayRoll object
       employeeDailyPayrollRepo.save(employeeDailyPayRoll);

        System.out.println(employeeDailyPayRoll);


    }

    public List<EmployeeDailyPayrollDto> getEmployeeDailypayroll() {
        List<EmployeeDailyPayRoll>employeeDailyPayRollList=employeeDailyPayrollRepo.findAll();
        return employeeDailyPayRollList.stream().map(this::convertToDTO).collect(Collectors.toList());


    }

    private EmployeeDailyPayrollDto convertToDTO(EmployeeDailyPayRoll employeeDailyPayRoll) {
        EmployeeDailyPayrollDto employeeDailyPayrollDto1 = new EmployeeDailyPayrollDto();
        employeeDailyPayrollDto1.setEmployee_daily_id(employeeDailyPayRoll.getEmployee_daily_id());
        employeeDailyPayrollDto1.setEmp_id(employeeDailyPayRoll.getEmp_id().getEmployee_id());
        employeeDailyPayrollDto1.setDaily_pay_id(employeeDailyPayRoll.getDaily_pay_id().getDaily_pay_roll_id());
        employeeDailyPayrollDto1.setWorking_hours(employeeDailyPayRoll.getWorking_hours());
        employeeDailyPayrollDto1.setOt_amount(employeeDailyPayRoll.getOt_amount());
        employeeDailyPayrollDto1.setShift_amount(employeeDailyPayRoll.getShift_amount());
        employeeDailyPayrollDto1.setTotal_amount(employeeDailyPayRoll.getTotal_amount());
        employeeDailyPayrollDto1.setDate(employeeDailyPayRoll.getDate());

        return employeeDailyPayrollDto1;
    }



}
