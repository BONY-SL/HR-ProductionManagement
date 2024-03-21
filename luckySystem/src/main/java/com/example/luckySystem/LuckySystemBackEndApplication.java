package com.example.luckySystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//example.app.jwtSecret = ap1hd
//example.app.jwtExpirationMs= 86400000
@SpringBootApplication
public class LuckySystemBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckySystemBackEndApplication.class, args);
	}
}
