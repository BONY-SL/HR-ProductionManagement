package com.example.luckySystem.dto.salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalDto {

    private Long employee_medical_id;
    private String emp_id;
    private String medical_status;
    private Date submit_date;
    private byte[] medical_report;
}
