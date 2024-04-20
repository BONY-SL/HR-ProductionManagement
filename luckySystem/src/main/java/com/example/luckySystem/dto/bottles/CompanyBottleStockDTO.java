package com.example.luckySystem.dto.bottles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyBottleStockDTO {

    private Date date;
    private long totalBottle;


}
