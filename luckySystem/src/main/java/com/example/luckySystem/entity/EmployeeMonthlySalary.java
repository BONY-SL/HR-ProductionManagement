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
@Table(name="employeemonthlysalary")
public class EmployeeMonthlySalary {

    @Id
    @Column(name="salary_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salary_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @Column(name="salary_type",length = 15,nullable = false)
    private String salary_type;

    @Column(name="job_role",length = 20,nullable = false)
    private String job_role;

    @Column(name="date",length = 20,nullable = false)
    private String date;

    @Column(name="total_basicsalary")
    private double total_basicsalary;

    @Column(name="bonus_amount")
    private double bonus_amount;

    @Column(name="total_otamount")
    private double total_otamount;

    @Column(name="allowancess_amount")
    private double allowancess_amount;

    @Column(name="deduction_amount")
    private double deduction_amount;

    @Column(name="epf",length = 20,nullable = false)
    private double epf;

    @Column(name="cetf",length = 20,nullable = false)
    private double cetf;

    @Column(name="etf",length = 20,nullable = false)
    private double etf;

    @Column(name="loan_deduction")
    private double loan_deduction;

    @Column(name="advance_salary")
    private double advance_salary;

    @Column(name="gross_basic_salary",nullable = false)
    private double gross_basic_salary;

    @Column(name="net_salary",nullable = false)
    private double net_salary;

}
