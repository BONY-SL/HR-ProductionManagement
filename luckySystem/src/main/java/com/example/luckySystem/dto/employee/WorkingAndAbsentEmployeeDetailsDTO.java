package com.example.luckySystem.dto.employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingAndAbsentEmployeeDetailsDTO {

    private String emp_id;
    private String name;
    private String department;
    private String section;
    private String jobRole;
    private String attendance_status;

}
