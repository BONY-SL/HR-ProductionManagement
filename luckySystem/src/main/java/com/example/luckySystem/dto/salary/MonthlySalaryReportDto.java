package com.example.luckySystem.dto.salary;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthlySalaryReportDto {
    private Long salary_id;
    private String emp_id;
    private String emp_Name;
    private String salary_type;
    private String date;
    private String job_role;
    private String month;
    private int year;
    private double total_basicsalary;
    private double bonus_amount;
    private double total_otamount;
    private double allowancess_amount;
    private double deduction_amount;
    private double epf;
    private double cepf;
    private double etf;
    private double loan_deduction;
    private double advance_salary;
    private double gross_basic_salary;
    private double net_salary;
}
