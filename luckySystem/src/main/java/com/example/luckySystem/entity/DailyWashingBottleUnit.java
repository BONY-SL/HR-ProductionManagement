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
@Table(name = "dailywashedbottlesunit")
public class DailyWashingBottleUnit {
    @Id
    @Column(name="washed_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long washed_id;

    @Column(name = "damage_bottles",nullable = false)
    private long damage_bottles;

    @Column(name = "submit_time",nullable = false)
    private Time submit_time;

    @Column(name = "submit_date",nullable = false)
    private Date submit_date;

    @Column(name = "for_production",nullable = false)
    private long for_production;
}
