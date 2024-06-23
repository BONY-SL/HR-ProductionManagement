package com.example.luckySystem.dto.user;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteUserRequestDto {
    private Long userId;
    private String deleteReason;
}
