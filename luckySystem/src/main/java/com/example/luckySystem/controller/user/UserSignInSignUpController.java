package com.example.luckySystem.controller.user;

import com.example.luckySystem.config.UserAuthenticationProvider;
import com.example.luckySystem.dto.user.CredentialsDto;
import com.example.luckySystem.dto.user.SignUpDto;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.exceptions.EmployeeIDAlreadyExistsException;
import com.example.luckySystem.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class UserSignInSignUpController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/userlogin")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/userregister")
    public ResponseEntity<?> register(@RequestBody @Valid SignUpDto user) {

        try {
            userService.register(user);

            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (EmployeeIDAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
