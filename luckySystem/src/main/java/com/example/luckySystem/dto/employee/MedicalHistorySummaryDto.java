package com.example.luckySystem.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalHistorySummaryDto {

    private long approvedCount;
    private long rejectedCount;
    private String empId;
    private String employeeName;
    private String jobRole;


}
