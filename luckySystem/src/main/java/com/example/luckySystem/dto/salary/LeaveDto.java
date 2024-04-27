package com.example.luckySystem.dto.salary;

import com.example.luckySystem.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class LeaveDto {

    private Long employee_leave_id;
    private String emp_id;

    private String leave_type;
    private String  reson;

    private String  status;
    private Date start_time;
    private Date end_time;
}
