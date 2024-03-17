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

@Entity(name="companybottlestock")
public class CompanyBottleStock {

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UpdatedID;

    @Column(name = "update_Date",nullable = false)
    private Date date;

    @Column(name = "total_Bottles",nullable = false)
    private long TotalBottle;

}
