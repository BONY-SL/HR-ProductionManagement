package com.example.luckySystem.controller.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {
    @NotBlank(message = "Recipient email is required")
    @Email(message = "Email should be valid")
    private String to;

    @NotBlank(message = "Subject is required")
    @Size(max = 1024, message = "Subject cannot be longer than 255 characters")
    private String content;

    @NotBlank(message = "Content is required")
    private String subject;
}