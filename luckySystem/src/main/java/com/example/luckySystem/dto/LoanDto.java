package com.example.luckySystem.dto;

import com.example.luckySystem.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanDto {

    private Long loan_id;
    private String emp_id;
    private double loan_amount;
    private double interest_amount;
    private String loan_details;
}
