package com.example.luckySystem.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveChartDTO {

    private String starDate;
    private String endDate;
    private String status;

}
