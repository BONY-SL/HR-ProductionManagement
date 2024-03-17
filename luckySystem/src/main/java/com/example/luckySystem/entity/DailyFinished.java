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

@Entity(name = "dailyfinished")
public class DailyFinished {

    @Id
    @Column(name="finished_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FinishedID;

    @Column(name = "amount",nullable = false)
    private long Amount;

    @Column(name = "batch_code",length = 50,nullable = false)
    private String BatchCode;

    @Column(name = "submit_time",nullable = false)
    private Time submitTime;

    @Column(name = "submit_date",length = 50,nullable = false)
    private String submitDate;

    // good ,bad
    @Column(name = "finished_status",length = 50)
    private String status;

}
