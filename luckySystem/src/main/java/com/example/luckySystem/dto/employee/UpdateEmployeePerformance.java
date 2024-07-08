package com.example.luckySystem.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeePerformance {

    private String employeeId;
    private String status;
    private String salaryType;
    private String jobRole;
}
