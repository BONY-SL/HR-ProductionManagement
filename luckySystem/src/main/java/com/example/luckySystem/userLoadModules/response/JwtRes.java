package com.example.luckySystem.userLoadModules.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JwtRes {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtRes(String token, Long id, String user_name, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = user_name;
        this.email = email;
        this.roles = roles;
    }

}
