package com.example.luckySystem.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name = "dailyemptybottleunit")
public class DailyEmptyBottleUnit {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private long empty_bottles;

    @Column
    private long damage_bottles;

    @Column(nullable = false)
    private LocalTime submit_time;


    @Column(nullable = false)
    private Date submit_date;

    @Column(nullable = false)
    private long for_washing;

}
