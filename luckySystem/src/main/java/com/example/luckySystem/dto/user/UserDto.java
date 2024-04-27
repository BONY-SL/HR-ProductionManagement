package com.example.luckySystem.dto.user;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String  email;
    private String contact;
    private String  roles ;
    private String employee;
    private String  token ;


}
