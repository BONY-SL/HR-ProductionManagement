package com.example.luckySystem.controller.user;


import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.service.user.EmailService;
import com.example.luckySystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPManage searailzeble;

    @Autowired
    private EmailService emailService;


    @PostMapping("/checkUserIDandUserEmail")
    public ResponseEntity< String > checkUserIDandUserEmail(@RequestParam(value = "userID",required = false) String userID,
                                                            @RequestParam(value = "email",required = false) String email) {

        System.out.println(userID+email);
        try {
            boolean userExist=userService.checkUserIDandUserEmail(userID,email);
            System.out.println(userExist);

            if(userExist) {
                return ResponseEntity.status(HttpStatus.CREATED).body("true");
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
            }
        } catch (AppException e) {
            return new ResponseEntity<>("InValid Employee ID Or Email", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sendMailToUser")
    synchronized public ResponseEntity<?> sendMailToUser(@RequestBody MailRequest mailRequest,
                                                         @RequestParam(value = "userID",required = false) String userID){

        System.out.println(mailRequest.getTo()+userID);
        try{
            String otpCode=generateOTP();

            OTPSearailzeble otpSearailzeble=new OTPSearailzeble();

            otpSearailzeble.setOTP(otpCode);
            otpSearailzeble.setEmployeeID(userID);
            otpSearailzeble.setEmail(mailRequest.getTo());

            searailzeble.serialize(otpCode,otpSearailzeble);

            String emailContent=mailRequest.getContent() +"\n \n Your OTP Code : "+otpCode;

            emailService.commonMailService(mailRequest.getTo(),mailRequest.getSubject(), emailContent);

            return ResponseEntity.status(HttpStatus.CREATED).body("true");


        }catch (AppException e) {
            return new ResponseEntity<>("Failed Send Email", HttpStatus.BAD_REQUEST);
        }
    }

    synchronized private String generateOTP(){
        synchronized (this){
            Random random = new Random();
            int otp =10000+random.nextInt(90000000);
            return String.valueOf(otp);
        }
    }

    @PostMapping("/verifyGetOtp")
    public ResponseEntity<?> verifyGetOtp(@RequestParam(value = "otp", required = false) String otp){
        try {
            OTPSearailzeble userMailAndOTPDeSerailzeble=searailzeble.deserialize(otp);
            System.out.println(userMailAndOTPDeSerailzeble.getOTP());
            System.out.println(userMailAndOTPDeSerailzeble.getEmployeeID());
            System.out.println(userMailAndOTPDeSerailzeble.getEmail());

            if (!userMailAndOTPDeSerailzeble.getOTP().isEmpty() && !userMailAndOTPDeSerailzeble.getEmail().isEmpty() && userMailAndOTPDeSerailzeble.getOTP().equals(otp)) {
                return ResponseEntity.ok(userMailAndOTPDeSerailzeble.getOTP());
            } else {
                return ResponseEntity.ok("false");
            }
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid OTP Code");
        }
    }

    @PutMapping ("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam(value = "otp", required = false) String otp,
                                           @RequestParam(value = "password", required = false) String password){

        try {
            OTPSearailzeble userMailAndOTPDeSerailzeble=searailzeble.deserialize(otp);

            userService.resetPassword(userMailAndOTPDeSerailzeble,password);

            File file = new File(otp + ".ser");
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Serialized file deleted successfully");
                } else {
                    System.err.println("Failed to delete the serialized file");
                }
            } else {
                System.err.println("Serialized file not found");
            }

            return ResponseEntity.ok(userMailAndOTPDeSerailzeble);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid User Details");
        }

    }
}
