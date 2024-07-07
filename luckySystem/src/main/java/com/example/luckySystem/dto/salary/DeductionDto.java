package com.example.luckySystem.dto.salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DeductionDto {

    private Long deduction_id;
    private String salary_type;
    private String job_role;
    private String department_name;
    private String section_name;
    private String deduction_type;
    private double deduction_amount;


}
