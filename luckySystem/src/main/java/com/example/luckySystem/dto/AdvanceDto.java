package com.example.luckySystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdvanceDto {

    private Long advance_salary_id;

    private String emp_id;

    private String reson;

    private String status;

    private double amount;
}
