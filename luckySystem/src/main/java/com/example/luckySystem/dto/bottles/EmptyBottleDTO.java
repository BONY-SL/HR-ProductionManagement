package com.example.luckySystem.dto.bottles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmptyBottleDTO {
    private Long id;
    private long empty_bottles;
    private long damage_bottles;
    private LocalTime submit_time;
    private Date submit_date;
    private long for_washing;
}
