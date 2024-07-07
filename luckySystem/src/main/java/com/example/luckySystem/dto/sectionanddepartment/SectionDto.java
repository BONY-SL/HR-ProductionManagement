package com.example.luckySystem.dto.sectionanddepartment;

import com.example.luckySystem.entity.Department;
import com.example.luckySystem.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDto {


    private String section_id;


    private String section_name;


    private Date start_date;


    private String dep_id;


    private String section_of_head;
}
