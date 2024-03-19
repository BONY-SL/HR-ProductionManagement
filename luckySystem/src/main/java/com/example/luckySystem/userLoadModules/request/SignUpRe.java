package com.example.luckySystem.userLoadModules.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class SignUpRe {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
    @NotBlank
    @Size(max = 70)
    @Email
    private String email;

    @NotBlank
    private String contact;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public Set<String> getRole() {
        return role;
    }

    private Set<String> role;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
