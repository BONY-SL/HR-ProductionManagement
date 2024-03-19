package com.example.luckySystem.userLoadModules.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRe {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
