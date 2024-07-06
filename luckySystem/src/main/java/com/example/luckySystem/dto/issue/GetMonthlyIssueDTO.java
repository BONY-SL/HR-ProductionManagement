package com.example.luckySystem.dto.issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMonthlyIssueDTO {

    private String issue_name;
    private long count;
    private long total_damage_amount;

}
