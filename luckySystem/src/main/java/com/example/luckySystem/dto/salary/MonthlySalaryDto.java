package com.example.luckySystem.dto.salary;

import com.example.luckySystem.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class MonthlySalaryDto {



    private Long salary_id;
    private String emp_id;
    private String salary_type;
    private String job_role;
    private String date;
    private double bonus_amount;
    private double allowancess_amount;
    private double deduction_amount;
    private double epf;
    private double etf;
    private double loan_deduction;
    private double advance_salary;
    private double gross_basic_salary;
    private double net_salary;
}
