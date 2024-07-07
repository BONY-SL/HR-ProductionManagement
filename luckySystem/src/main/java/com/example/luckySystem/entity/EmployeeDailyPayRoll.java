package com.example.luckySystem.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name="employeedailypayroll")
public class EmployeeDailyPayRoll {

    @Id
    @Column(name="employee_daily_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_daily_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @ManyToOne
    @JoinColumn(name = "daily_pay_id",referencedColumnName = "daily_pay_roll_id")
    private DailyPayRoll daily_pay_id;

    @Column(name="working_hours",nullable = false)
    private double working_hours;

    @Column(name="ot_amount",nullable = false)
    private double ot_amount;

    @Column(name="shift_amount",nullable = false)
    private double shift_amount;

    @Column(name="total_amount",nullable = false)
    private double total_amount;

    @Column(name="date",nullable = false,length = 15)
    private Date date;


}
