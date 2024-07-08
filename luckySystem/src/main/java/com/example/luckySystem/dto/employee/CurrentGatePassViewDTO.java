package com.example.luckySystem.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentGatePassViewDTO {

    private String emp_id;
    private String name;
    private String department;
    private String section;
    private String jobRole;
    private String status;
    private Time in_time;
    private Time out_time;

}
