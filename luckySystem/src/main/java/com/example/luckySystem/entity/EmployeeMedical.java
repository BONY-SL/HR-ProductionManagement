package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name = "employeemedical")
public class EmployeeMedical {

    @Id
    @Column(name="employee_medical_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_medical_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @Column(name="medical_status",length = 200)
    private String medical_status;

    @Column(name="submit_date",length = 10)
    private Date submit_date;

    @Column(name="medical_report")
    private byte[] medical_report;

}
