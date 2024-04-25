package com.example.luckySystem;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

//example.app.jwtSecret = ap1hd
//example.app.jwtExpirationMs= 86400000
@SpringBootApplication
public class LuckySystemBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckySystemBackEndApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
