package com.example.luckySystem.dto.issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueReportDTO {

    private long numberOfBottles;
    private String issueType;
}
