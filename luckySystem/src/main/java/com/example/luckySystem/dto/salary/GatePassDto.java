package com.example.luckySystem.dto.salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data


public class GatePassDto {


    private Long employee_gate_pass_id;
    private String emp_id;
    private Time in_time;
    private Time out_time;
    private Date date;
    private String  reson;
    private String  status;


}
