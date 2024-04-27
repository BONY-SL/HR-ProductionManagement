package com.example.luckySystem.dto.salary;

import com.example.luckySystem.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AttendanceDto {
    private Long employee_attendance_id;
    private String emp_id;
    private String attendance_status;
    private Time in_time;
    private Time out_time;
    private Date date;
}
