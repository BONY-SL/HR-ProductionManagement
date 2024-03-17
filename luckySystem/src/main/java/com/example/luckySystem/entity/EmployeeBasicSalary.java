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

@Entity(name="employeebasicsalary")
public class EmployeeBasicSalary {

    @Id
    @Column(name="employee_basic_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmployeeBasicSalaryID;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "basic_salary_id",referencedColumnName = "basicsalary_id")
    private BasicSalary basicSalary;

    @Column(name="monthly_br_1")
    private double br_1;

    @Column(name="monthly_br_2")
    private double br_2;

    @Column(name="attend_days")
    private int initial_days;

    @Column(name="monthly_nopay_amount")
    private double initial_nopay_amount;

    @Column(name="monthly_basic_amount")
    private double basic_amount;

    @Column(name="monthly_get_pass_amount")
    private double get_pass_amount;

    @Column(name="monthly_ot_amount")
    private double ot_amount;

    @Column(name="monthly_subsistant")
    private double subsistant;
}
