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

@Entity(name="employebankaccount")
public class EmployeeBankAccount {

    @Id
    @Column(name="employee_bank_account_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long EmployeeBankAccountID;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name = "acount_name",length = 100,nullable = false)
    private String AcountName;


}
