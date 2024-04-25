package com.example.luckySystem.dto;

import com.example.luckySystem.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalDto {

    private Long employee_medical_id;
    private String emp_id;
    private String medical_status;
    private Date submit_date;
    private byte[] medical_report;
}
