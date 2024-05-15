package com.example.luckySystem.dto.bottles;


import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentBottleStatusDTO {


    private long woshing;
    private long production;
    private long lording;
}
