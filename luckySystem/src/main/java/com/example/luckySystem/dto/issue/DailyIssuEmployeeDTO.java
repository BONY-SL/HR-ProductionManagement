package com.example.luckySystem.dto.issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyIssuEmployeeDTO {

    private Long daily_issue_id;
    private String emp_id;
    private String issue_name;
    private int damage_amount;
    private Date submit_date;
}
