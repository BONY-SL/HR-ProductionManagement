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

@Entity(name="employeelone")
@Table(name = "employeelone")
public class EmployeeLoan {

    @Id
    @Column(name="loan_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @Column(name="loan_amount",nullable = false)
    private double loan_amount;

    @Column(name="interest_amount",nullable = false)
    private double interest_amount;

    @Column(name="loan_details",length = 200,nullable = false)
    private String loan_details;

}
