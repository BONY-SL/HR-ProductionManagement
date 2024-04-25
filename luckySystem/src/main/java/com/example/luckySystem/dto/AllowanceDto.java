package com.example.luckySystem.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class AllowanceDto {

    private Long allowances_id;
    private String salary_type;
    private String job_role;
    private String department_name;
    private String section_name;
    private String allowances_type;
    private double allowances_amount;


}
