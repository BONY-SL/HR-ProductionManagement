package com.example.luckySystem.controller.user;

import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class UserController {

    @Autowired
    private UserService service;


    @PutMapping("/updateUserDetails")
    public ResponseEntity<?> updateUserDetails(@RequestBody UserDto dto) {
        service.updateUserDetails(dto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/deleteUserDetails/{userId}")
    public ResponseEntity<?> deleteUserDetails(@PathVariable Long userId) {
        service.deleteUserDetails(userId);
        return ResponseEntity.ok().build();
    }

}
