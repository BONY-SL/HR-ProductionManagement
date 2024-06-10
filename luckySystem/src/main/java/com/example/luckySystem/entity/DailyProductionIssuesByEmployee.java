package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name = "dailyproductionissues")
public class DailyProductionIssuesByEmployee {

    @Id
    @Column(name="daily_issue_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daily_issue_id;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee emp_id;

    @Column(name = "issue_name",length = 50,nullable = false)
    private String issue_name;

    @Column(name = "damage_amount",nullable = false)
    private int damage_amount;

    @Column(name = "submit_date",nullable = false)
    private Date submit_date;
}
