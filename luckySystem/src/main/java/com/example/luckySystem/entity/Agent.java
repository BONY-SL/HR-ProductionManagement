package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name = "agent")
public class Agent {

    @Id
    @Column(name="agent_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long AgentID;

    @Column(name = "agent_name",length = 50,nullable = false)
    private String AgentName;

    @Column(name = "agency_name",length = 50,nullable = false)
    private String AgencyName;

    @Column(name = "address",length = 50,nullable = false)
    private String Address;

    @Column(name = "email",length = 50,nullable = false)
    private String Email;

    @Column(name = "contact_number",length = 50,nullable = false)
    private String ContactNumber;

}
