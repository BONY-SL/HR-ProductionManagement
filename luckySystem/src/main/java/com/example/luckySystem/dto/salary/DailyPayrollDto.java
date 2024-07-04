package com.example.luckySystem.dto.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyPayrollDto {


    private Long daily_pay_roll_id;


    private String salary_type;


    private String job_role;


    private String department_name;


    private String section_name;


    private int  working_hours;


    private double  amount_per_aditonal_hour;

    private double  shift_amount;
}