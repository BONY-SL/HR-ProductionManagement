package com.example.luckySystem.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name="employeeattendance")
public class EmployeeAttendance {

    @Id
    @Column(name="employee_attendance_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_attendance_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    //Absent,Present,Late
    @Column(name="attendance_status",length = 15,nullable = false)
    private String attendance_status;

    @Column(name="in_time",nullable = false)
    private Time in_time;

    @Column(name="out_time")
    private Time out_time;

    @Column(name="date",nullable = false,length = 15)
    private Date date;

}
