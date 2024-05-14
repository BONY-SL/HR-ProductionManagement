package com.example.luckySystem.dto.agent;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentPurchaseDTO {


    private long amount;
    private Date submit_date;

}
