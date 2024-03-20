package com.example.luckySystem.userLoadModules.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
public class SignUpRe {


    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Size(max = 100)
    @Email
    private String email;

    @Size(min=8,max = 10)
    private String password;

    @Size(max = 10)
    private String contact;

    private Set<String> role;


}
