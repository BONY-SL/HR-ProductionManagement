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

@Entity(name = "dailyproductionissues")
public class DailyProductionIssuesByEmployee {

    @Id
    @Column(name="daily_issue_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DailyIssueID;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name = "issue_name",length = 50,nullable = false)
    private String IssuName;

    @Column(name = "damage_amount",nullable = false)
    private int IssueAmount;
}
