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

@Entity(name = "dailyemptybottleunit")
public class DailyEmptyBottleUnit {

    @Id
    @Column(name="empty_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long EmptyUnitID;

    @Column(name = "empty_bottles",nullable = false)
    private long EmptyBottles;

    @Column(name = "damage_bottles")
    private long DamageBottles;

    @Column(name = "submit_time",nullable = false)
    private Time submitTime;

    @Column(name = "submit_date",length = 50,nullable = false)
    private String submitDate;

    @Column(name = "for_washing",nullable = false)
    private long ForWashing;

}
