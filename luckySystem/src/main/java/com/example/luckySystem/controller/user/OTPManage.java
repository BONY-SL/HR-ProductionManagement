package com.example.luckySystem.controller.user;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class OTPManage {

    synchronized public void serialize(String filename, OTPSearailzeble otpSearailzeble) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename+".ser"))) {
            oos.writeObject(otpSearailzeble);
            System.out.println("User OTP serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred during serialization: " + e.getMessage());
        }
    }

    synchronized public OTPSearailzeble deserialize(String filename) {

        OTPSearailzeble userMailAndOTPSerailzeble = new OTPSearailzeble();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename+".ser"))) {

            userMailAndOTPSerailzeble = (OTPSearailzeble) ois.readObject();


            System.out.println("User OTP deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error occurred during deserialization: " + e.getMessage());
        }
        return userMailAndOTPSerailzeble;
    }
}
