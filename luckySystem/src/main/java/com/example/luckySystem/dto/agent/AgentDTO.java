package com.example.luckySystem.dto.agent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentDTO {

    private Long agent_id;
    private String agent_name;
    private String agency_name;
    private String address;
    private String email;
    private String contact_number;
    private Date deletedAt;
    private String deleteReason;
}
