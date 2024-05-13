package com.example.luckySystem.dto.user;


import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequestDTO {

    private Long id;
    private String username;
    private String password;
    private String  email;
    private String contact;

}
