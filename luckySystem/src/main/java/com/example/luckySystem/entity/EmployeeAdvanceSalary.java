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

@Entity(name="employeeadvancesalary")
public class EmployeeAdvanceSalary {

    @Id
    @Column(name="advance_salary_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advance_salary_id;

    @OneToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name="reson",length = 240,nullable = false)
    private String  loan_amount;

    @Column(name="status",length = 240,nullable = false)
    private String  status;

    @Column(name="amount",nullable = false)
    private double amount;
}
