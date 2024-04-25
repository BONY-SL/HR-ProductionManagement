package com.example.luckySystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicSalaryDto {
    private Long basic_salary_id;
    private String salary_type;
    private String job_role;
    private String department_name;
    private String section_name;
    private double br_1;
    private double br_2;
    private int initial_days;
    private double initial_nopay_amount;
    private double basic_amount;
    private double get_pass_amount;
    private double ot_amount;
    private double subsistant;
}


