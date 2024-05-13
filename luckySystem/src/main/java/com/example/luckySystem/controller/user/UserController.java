package com.example.luckySystem.controller.user;

import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.user.CredentialsDto;
import com.example.luckySystem.dto.user.SignUpDto;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.dto.user.UserUpdateRequestDTO;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.exceptions.EmployeeIDAlreadyExistsException;
import com.example.luckySystem.service.user.EmailService;
import com.example.luckySystem.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    EmailService emailService;

    @PostMapping("/createNewUser")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid SignUpDto userDto) {
        System.out.println(userDto);
        try {
            userService.register(userDto);

            return new ResponseEntity<>("New User Created successfully", HttpStatus.CREATED);
        } catch (EmployeeIDAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sendmailToUser")
    public ResponseEntity<?> sendMailToUser(@RequestBody  MailRequest mailRequest) {
        System.out.println("Controller"+mailRequest);
            emailService.sendUserCredentials(mailRequest.getTo(),mailRequest.getSubject(),mailRequest.getContent());
        return new ResponseEntity<>("Email Send successfully", HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteUserDetails/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserDetails(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }

    @GetMapping("/getallUsers")
    public ResponseEntity<List<UserDto>> getallUsers() {
        List<UserDto> user = userService.getallUsers();
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/updateUserProfile")
    public ResponseEntity<UserDto> updateUserProfile(@RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) {
        System.out.println(userUpdateRequestDTO);
        UserDto userDto = userService.updateUserProfile(userUpdateRequestDTO);
        System.out.println(userDto);
        return ResponseEntity.ok(userDto);
    }

}
