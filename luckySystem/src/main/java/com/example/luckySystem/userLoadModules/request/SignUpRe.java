package com.example.luckySystem.userLoadModules.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class SignUpRe {

    @NotBlank
    private String user_name;

    @NotBlank
    private String user_password;
    @NotBlank
    @Size(max = 70)
    @Email
    private String email;

    @NotBlank
    private String contact_number;

    private Set<String> role;

}
