package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name="employeegetpass")
public class EmployeeGatePass {

    @Id
    @Column(name="employee_gate_pass_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_gate_pass_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @Column(name="in_time",nullable = false)
    private Time in_time;

    @Column(name="out_time")
    private Time out_time;

    @Column(name="date",nullable = false,length = 15)
    private Date date;

    @Column(name="reson",length = 15)
    private String  reson;

    @Column(name="status",length = 15)
    private String  status;
}
