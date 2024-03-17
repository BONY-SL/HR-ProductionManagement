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

@Entity(name = "dailywashedbottlesunit")
public class DailyWashingBottleUnit {
    @Id
    @Column(name="washed_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long WashedUnitID;

    @Column(name = "damage_bottles",nullable = false)
    private long DamageBottles;

    @Column(name = "submit_time",nullable = false)
    private Time submitTime;

    @Column(name = "submit_date",length = 50,nullable = false)
    private String submitDate;

    @Column(name = "for_production",nullable = false)
    private long ForProduction;
}
