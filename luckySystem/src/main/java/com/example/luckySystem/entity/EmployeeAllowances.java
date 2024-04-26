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
@Table(name="employeeallowances")
public class EmployeeAllowances {

    @Id
    @Column(name="employee_allowances_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_allowances_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @ManyToOne
    @JoinColumn(name = "alow_id",referencedColumnName = "allowances_id")
    private Allowances alow_id;

    @Column(name="allowances_type",length = 50,nullable = false)
    private String allowances_type;

    @Column(name="allowances_amount",nullable = false)
    private double allowances_amount;
}
