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
@Table(name="allowances")
public class Allowances{

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allowances_id;

    @Column(length = 15,nullable = false)
    private String salary_type;

    @Column(length = 20,nullable = false)
    private String job_role;

    @Column(length = 50,nullable = false)
    private String department_name;

    @Column(length = 50,nullable = false)
    private String section_name;

    @Column(length = 50,nullable = false)
    private String allowances_type;

    @Column(nullable = false)
    private double allowances_amount;

}
