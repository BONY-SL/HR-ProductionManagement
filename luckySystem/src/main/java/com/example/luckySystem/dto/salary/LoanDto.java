package com.example.luckySystem.dto.salary;
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
