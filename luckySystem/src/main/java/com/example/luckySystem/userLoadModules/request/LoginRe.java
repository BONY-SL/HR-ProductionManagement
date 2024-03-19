package com.example.luckySystem.userLoadModules.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRe {
    @NotBlank
    private String user_name;

    @NotBlank
    private String user_password;


}
