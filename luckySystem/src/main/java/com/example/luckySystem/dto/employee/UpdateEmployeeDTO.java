package com.example.luckySystem.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateEmployeeDTO {

    private String employeeid;

    private String employee_name;

    private String address;

    private String gender;

    private String ma_uma;

    private String contact;

    private String dep_id;

    private String sec_id;



}
