package com.example.luckySystem.controller;

import com.example.luckySystem.config.UserAuthenticationProvider;
import com.example.luckySystem.dto.CredentialsDto;
import com.example.luckySystem.dto.SignUpDto;
import com.example.luckySystem.dto.UserDto;
import com.example.luckySystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
public class UserSignInSignUpController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/userlogin")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/useregister")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {

        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create(createdUser.getUsername())).body(createdUser);

    }

    @GetMapping("/getuserdetailsByID/{userId}")
    public UserDto getUserDetails(@PathVariable String userId){
        return userService.getDetailsById(userId);
    }

    @PutMapping("/updateuserDetails")
    public UserDto updateUserDetails(@RequestBody UserDto userDto){
        System.out.println("work");
        return userService.updateUserDetails(userDto);
    }


}
