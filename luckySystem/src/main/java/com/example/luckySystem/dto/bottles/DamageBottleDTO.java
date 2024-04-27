package com.example.luckySystem.dto.bottles;


import com.example.luckySystem.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DamageBottleDTO {

    private Long daily_damage_id;
    private String unit_type;
    private String employee_id;
    private int damage_amount;
    private Date date;
}
