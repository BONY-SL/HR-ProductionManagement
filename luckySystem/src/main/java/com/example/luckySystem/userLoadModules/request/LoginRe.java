package com.example.luckySystem.userLoadModules.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRe {
    @NotBlank
    private String username;

    @NotBlank
    private String userpassword;


    public String getUsername() {
        return username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
