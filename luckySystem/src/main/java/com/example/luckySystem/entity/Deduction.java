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
@Table(name="deduction")
public class Deduction{

    @Id
    @Column(name="deduction_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deduction_id;

    @Column(name="salary_type",length = 15,nullable = false)
    private String salary_type;

    @Column(name="job_role",length = 20,nullable = false)
    private String job_role;

    @Column(name="department_name",length = 50,nullable = false)
    private String department_name;

    @Column(name="section_name",length = 50,nullable = false)
    private String section_name;

    @Column(name="deduction_type",length = 50,nullable = false)
    private String deduction_type;

    @Column(name="deduction_amount",nullable = false)
    private double deduction_amount;
}
