package com.example.luckySystem.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name="employeedailypayroll")
public class EmployeeDailyPayRoll {

    @Id
    @Column(name="employee_daily_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daily_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "daily_pay_id",referencedColumnName = "daily_pay_roll_id")
    private DailyPayRoll dailyPayRoll;

    @Column(name="working_hours",nullable = false)
    private int working_hours;

    @Column(name="ot_amount",nullable = false)
    private double OTAmount;

    @Column(name="shift_amount",nullable = false)
    private double shiftAmount;

    @Column(name="total_amount",nullable = false)
    private double TotalAmount;

}
