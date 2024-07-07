package com.example.luckySystem.dto.sectionanddepartment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class DepartmentDto {

    private String department_id;
    private String department_name;
    private Date start_date;
    private String head_of_department;


}
