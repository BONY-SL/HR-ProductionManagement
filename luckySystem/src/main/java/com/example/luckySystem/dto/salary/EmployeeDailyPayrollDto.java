package com.example.luckySystem.dto.salary;
import com.example.luckySystem.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDailyPayrollDto {

    private Long employee_daily_id;
    private String  emp_id;
    private Long daily_pay_id;
    private double working_hours;
    private double ot_amount;
    private double late_amount;
    private double gatepass_amount;
    private double shift_amount;
    private double total_amount;
    private Date date;


}