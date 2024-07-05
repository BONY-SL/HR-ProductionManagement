package com.example.luckySystem.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name = "agent")
public class Agent {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agent_id;

    @Column(length = 50,nullable = false)
    private String agent_name;

    @Column(length = 50,nullable = false)
    private String agency_name;

    @Column(length = 50,nullable = false)
    private String address;

    @Column(length = 50,nullable = false)
    @Email
    private String email;

    @Column(length = 12,nullable = false)
    private String contact_number;

    @Column(nullable = true)
    private Date deletedAt;

    @Column(nullable = true, length = 500)
    private String deleteReason;

}
