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

@Entity(name="basicsalary")
public class BasicSalary{

    @Id
    @Column(name="basicsalary_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BasicSalary_id;

    @Column(name="salary_type",length = 15,nullable = false)
    private String salary_type;

    @Column(name="job_role",length = 20,nullable = false)
    private String job_role;

    @Column(name="department_Name",length = 50,nullable = false)
    private String departmentName;

    @Column(name="section_Name",length = 50,nullable = false)
    private String sectionName;

    @Column(name="br_1")
    private double br_1;

    @Column(name="br_2")
    private double br_2;

    @Column(name="initial_days")
    private int initial_days;

    @Column(name="initial_nopay_amount")
    private double initial_nopay_amount;

    @Column(name="basic_amount")
    private double basic_amount;

    @Column(name="get_pass_amount")
    private double get_pass_amount;

    @Column(name="ot_amount")
    private double ot_amount;

    @Column(name="subsistant")
    private double subsistant;
}
