package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name="employeeattendance")
public class EmployeeAttendance {

    @Id
    @Column(name="employee_attendance_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AttendanceID;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    //Absent,Present,Late
    @Column(name="attendance_status",length = 15,nullable = false)
    private String AttendanceStatus;

    @Column(name="in_time",nullable = false)
    private Time InTime;

    @Column(name="out_time")
    private Time OutTime;

    @Column(name="date",nullable = false,length = 15)
    private String date;

}
