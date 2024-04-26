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
@Table(name = "productionissue")
public class ProductionIssue {

    @Id
    @Column(name="issue_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issue_id;

    @Column(name = "issue_name",length = 50,nullable = false)
    private String issue_name;
}
