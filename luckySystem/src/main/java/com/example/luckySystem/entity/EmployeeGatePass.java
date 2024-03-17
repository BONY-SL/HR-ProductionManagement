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

@Entity(name="employeegetpass")
public class EmployeeGatePass {

    @Id
    @Column(name="employee_gate_pass_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmployeeGatePassID;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name="in_time",nullable = false)
    private Time InTime;

    @Column(name="out_time")
    private Time OutTime;

    @Column(name="date",nullable = false,length = 15)
    private String date;

    @Column(name="reson",length = 15)
    private String  Reson;

    @Column(name="status",length = 15)
    private String  Status;
}
