package com.example.luckySystem.dto.issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueDTO {

    private Long issue_id;
    private String issue_name;
}
