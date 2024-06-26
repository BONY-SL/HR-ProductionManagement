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
@Table(name="employeededuction")
public class EmployeeDeduction {

    @Id
    @Column(name="employee_deduction_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_deduction_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @ManyToOne
    @JoinColumn(name = "de_id",referencedColumnName = "deduction_id")
    private Deduction de_id;

    @Column(name="deduction_type",length = 50,nullable = false)
    private String deduction_type;

    @Column(name="deduction_amount",nullable = false)
    private double deduction_amount;
}
