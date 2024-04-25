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

@Entity
@Table(name="dailypayroll")
public class DailyPayRoll{

    @Id
    @Column(name="daily_pay_roll_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daily_pay_roll_id;

    @Column(name="salary_type",length = 15,nullable = false)
    private String salary_type;

    @Column(name="job_role",length = 20,nullable = false)
    private String job_role;

    @Column(name="department_name",length = 50,nullable = false)
    private String department_name;

    @Column(name="section_name",length = 50,nullable = false)
    private String section_name;

    @Column(name="working_hours",nullable = false)
    private int  working_hours;

    @Column(name="amount_per_aditonal_hour",nullable = false)
    private double  amount_per_aditonal_hour;

    @Column(name="shift_amount",nullable = false)
    private double  shift_amount;


}
