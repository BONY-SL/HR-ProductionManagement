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
@Table(name = "employeeleave")
public class EmployeeLeave {

    @Id
    @Column(name = "employee_leave_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_leave_id;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "employee_id")
    private Employee emp_id;

    //Absent,Present,Late
    @Column(name = "leave_type", length = 15)
    private String leave_type;

    @Column(name = "reson", length = 15)
    private String reson;

    @Column(name = "status", length = 15)
    private String status;

    @Column(name = "start_time", length = 15)
    private Date start_time;

    @Column(name = "end_time", length = 15)
    private Date end_time;
}
