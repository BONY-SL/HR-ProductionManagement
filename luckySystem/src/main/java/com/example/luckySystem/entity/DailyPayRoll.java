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

@Entity(name="dailypayroll")
public class DailyPayRoll{

    @Id
    @Column(name="daily_pay_roll_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DailyPayRoll_id;

    @Column(name="salary_type",length = 15,nullable = false)
    private String salary_type;

    @Column(name="job_role",length = 20,nullable = false)
    private String job_role;

    @Column(name="department_Name",length = 50,nullable = false)
    private String departmentName;

    @Column(name="section_Name",length = 50,nullable = false)
    private String sectionName;

    @Column(name="working_hours",nullable = false)
    private int  working_hours;

    @Column(name="amount_per_aditonal_hour",nullable = false)
    private double  amount_per_aditonal_hour;

    @Column(name="shift_amount",nullable = false)
    private double  shift_amount;


}
