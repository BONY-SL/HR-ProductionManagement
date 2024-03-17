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

@Entity(name = "employee")
public class Employee {

    //"EPF00001"
    @Id
    @Column(name="employee_id",length = 8,unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Employee_id;

    @Column(name = "job_role",length = 50,nullable = false)
    private String JobRole;

    @Column(name = "salary_type",length = 50,nullable = false)
    private String SalaryType;

    @Column(length = 200,nullable = false)
    private String EmployeeName;

    @Column(length = 200,nullable = false)
    private String DOB;

    @Column(length = 200,nullable = false)
    private String Address;

    @Column(length = 6,nullable = false)
    private String Gender;

    @Column(length = 10,nullable = false)
    private String MarriedUnMarriedStatus;

    @Column(length = 10,nullable = false)
    private String ContactNumber;

    //permanent or temporary
    @Column(length = 200,nullable = false)
    private String CompanyStatus;

    @Column(name="CV_file",nullable = false)
    private byte[] Cv;

    @ManyToOne
    @JoinColumn(name = "depid",referencedColumnName = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "sec_id",referencedColumnName = "section_id")
    private Section section;

}
