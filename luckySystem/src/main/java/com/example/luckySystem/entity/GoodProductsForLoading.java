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

@Entity(name = "productionstoloading")
public class GoodProductsForLoading {

    @Id
    @Column(name="loading_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LordingID;

    @Column(name = "amount",nullable = false)
    private long Amount;

    @Column(name = "batch_code",length = 50,nullable = false)
    private String BatchCode;

    @Column(name = "submit_time",nullable = false)
    private Time submitTime;

    @Column(name = "submit_date",length = 50,nullable = false)
    private String submitDate;

    @ManyToOne
    @JoinColumn(name = "ag_id",referencedColumnName = "agent_id")
    private Agent agent;
}
