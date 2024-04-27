package com.example.luckySystem.dto.employee;

import com.example.luckySystem.entity.Department;
import com.example.luckySystem.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private String employeeid;

    private String job_role;

    private String salary_type;

    private String employee_name;

    private Date dob;

    private String address;

    private String gender;

    //Marred Un Marred Status
    private String ma_uma;

    private String contact;

    //permanent or temporary

    private String company_status;

    private byte[] cv;

    private String dep_id;

    private String sec_id;
}
