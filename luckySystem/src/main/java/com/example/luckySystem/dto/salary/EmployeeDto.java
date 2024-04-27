package com.example.luckySystem.dto.salary;

import com.example.luckySystem.entity.Department;
import com.example.luckySystem.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {


    private String employee_id;
    private String job_role;
    private String salary_type;
    private String employee_name;
    private Date dob;
    private String address;
    private String gender;
    private String ma_uma;
    private String contact;
    private String company_status;
    private byte[] cv;
    private String dep_id;
    private String sec_id;

}
