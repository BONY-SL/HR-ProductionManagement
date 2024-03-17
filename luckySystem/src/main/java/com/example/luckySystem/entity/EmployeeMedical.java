package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name = "employeemedical")
public class EmployeeMedical {

    @Id
    @Column(name="employee_medical_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MedicalID;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name="medical_status",length = 200)
    private String MedicalStatus;

    @Column(name="submit_date",length = 10)
    private String  date;

    @Column(name="medical_report")
    private byte[] OutTime;

}
