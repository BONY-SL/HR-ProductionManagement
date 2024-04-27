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
@Table(name = "dailyfinished")
public class DailyFinished {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long finished_id;

    @Column(nullable = false)
    private long amount;

    @Column(length = 50,nullable = false)
    private String batch_code;

    @Column(nullable = false)
    private LocalTime submit_time;


    @Column(nullable = false)
    private Date submit_date;

    // good ,bad
    @Column(length = 10)
    private String finished_status;

}
