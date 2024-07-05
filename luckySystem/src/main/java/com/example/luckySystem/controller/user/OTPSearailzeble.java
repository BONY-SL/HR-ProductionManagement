package com.example.luckySystem.controller.user;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

@Getter
@Setter
@Controller
public class OTPSearailzeble implements Serializable {

    private String OTP;
    private String employeeID;
    private String email;
}
