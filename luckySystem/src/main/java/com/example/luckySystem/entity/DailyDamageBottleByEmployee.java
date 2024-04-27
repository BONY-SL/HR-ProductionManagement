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
@Table(name = "dailybottledamage")
public class DailyDamageBottleByEmployee {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daily_damage_id;

    // Empty,Washed
    @Column(length = 50,nullable = false)
    private String unit_type;

    @ManyToOne
    @JoinColumn(name="employee_id",referencedColumnName = "employee_id")
    private Employee employee;


    @Column(nullable = false)
    private int damage_amount;

    @Column(nullable = false)
    private Date date;

}
