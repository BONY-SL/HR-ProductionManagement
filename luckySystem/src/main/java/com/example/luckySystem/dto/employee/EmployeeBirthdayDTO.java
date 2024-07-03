package com.example.luckySystem.dto.employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeBirthdayDTO {

    private String employee_id;
    private String job_role;
    private String employee_name;
    private String gender;
    private String contact;
    private String dep_id;
    private String sec_id;
}
