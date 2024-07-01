package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component
@Entity(name = "employee")
@Table(name="employee")
public class Employee {

    @Id
    @Column(name="employee_id", length = 8, unique = true, nullable = false)
    private String employee_id;

    @Column(name = "job_role", length = 50, nullable = false)
    private String job_role;

    @Column(name = "salary_type", length = 50, nullable = false)
    private String salary_type;

    @Column(length = 200, nullable = false)
    private String employee_name;

    @Column(nullable = false)
    private Date dob;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 6, nullable = false)
    private String gender;

    @Column(length = 10, nullable = false)
    private String ma_uma;

    @Column(length = 12, nullable = false)
    private String contact;

    @Column(length = 200, nullable = false)
    private String company_status;

    @Column(name="cv", nullable = true, columnDefinition="LONGBLOB")
    private byte[] cv;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "sec_id", referencedColumnName = "section_id")
    private Section sec_id;
}
