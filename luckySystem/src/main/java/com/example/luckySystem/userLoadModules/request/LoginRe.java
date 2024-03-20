package com.example.luckySystem.userLoadModules.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRe {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
