package com.example.luckySystem.dto.bottles;


import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class CurrentBottleStatusDTO implements Serializable {

    private long woshing;
    private long production;
    private long lording;

}


