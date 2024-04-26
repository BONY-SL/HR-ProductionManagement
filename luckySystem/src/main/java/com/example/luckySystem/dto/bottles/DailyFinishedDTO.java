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
public class DailyFinishedDTO {

    private Long finished_id;
    private long amount;
    private String batch_code;
    private LocalTime submit_time;
    private Date submit_date;
    private String finished_status;
}
