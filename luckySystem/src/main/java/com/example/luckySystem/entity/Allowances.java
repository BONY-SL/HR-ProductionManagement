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

@Entity(name="allowances")
public class Allowances{

    @Id
    @Column(name="allowances_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Allowances_id;

    @Column(name="salary_type",length = 15,nullable = false)
    private String salary_type;

    @Column(name="job_role",length = 20,nullable = false)
    private String job_role;

    @Column(name="department_Name",length = 50,nullable = false)
    private String departmentName;

    @Column(name="section_Name",length = 50,nullable = false)
    private String sectionName;

    @Column(name="allowances_type",length = 50,nullable = false)
    private String allowances_type;

    @Column(name="allowances_amount",nullable = false)
    private double allowances_amount;

}
