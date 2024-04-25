package com.example.luckySystem.entity;


import jakarta.persistence.*;
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
@Table(name="companybottlestock")
public class CompanyBottleStock {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long updated_id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private long total_bottle;

}
