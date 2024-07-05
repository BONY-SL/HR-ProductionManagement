package com.example.luckySystem.dto.agent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentReportDTO {

    private String agentName;
    private String agencyName;
    private long totalAmount;

}
