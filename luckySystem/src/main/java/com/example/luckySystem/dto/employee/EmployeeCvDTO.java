package com.example.luckySystem.dto.employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCvDTO {

    private String employee_id;
    private byte[] cv;
}
