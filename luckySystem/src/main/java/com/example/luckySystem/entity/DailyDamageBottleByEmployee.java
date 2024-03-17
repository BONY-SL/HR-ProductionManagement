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

@Entity(name = "dailybottledamage")
public class DailyDamageBottleByEmployee {

    @Id
    @Column(name="daily_damage_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DailyDamageID;

    // Empty,Washed
    @Column(name = "unit_type",length = 50,nullable = false)
    private String UnitType;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name = "damage_amount",nullable = false)
    private int DamageAmount;

    @Column(name = "date",nullable = false,length = 50)
    private String date;

}
