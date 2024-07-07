package com.example.luckySystem.dto.salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
