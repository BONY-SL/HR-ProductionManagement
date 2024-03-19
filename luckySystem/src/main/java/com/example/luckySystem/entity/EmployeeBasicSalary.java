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
@Table(name="employeebasicsalary")
public class EmployeeBasicSalary {

    @Id
    @Column(name="employee_basic_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_basic_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @ManyToOne
    @JoinColumn(name = "basic_id",referencedColumnName = "basic_salary_id")
    private BasicSalary basic_id;

    @Column(name="monthly_br_1")
    private double monthly_br_1;

    @Column(name="monthly_br_2")
    private double monthly_br_2;

    @Column(name="attend_days")
    private int attend_days;

    @Column(name="monthly_nopay_amount")
    private double monthly_nopay_amount;

    @Column(name="monthly_basic_amount")
    private double monthly_basic_amount;

    @Column(name="monthly_get_pass_amount")
    private double monthly_get_pass_amount;

    @Column(name="monthly_ot_amount")
    private double monthly_ot_amount;

    @Column(name="monthly_subsistant")
    private double monthly_subsistant;
}
