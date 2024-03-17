package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.stereotype.Component;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name="department")
public class Department {

    @Id
    @Column(name="department_id",length = 5,unique = true,nullable = false)
    private String department_id;

    @Column(name="department_name",length = 50,nullable = false)
    private String departmentName;

    @Column(name="start_date",length = 20,nullable = false)
    private String startDate;

    @OneToOne
    @JoinColumn(name = "head_of_department",referencedColumnName = "employee_id")
    private Employee employee;



}
