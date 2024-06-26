package com.example.luckySystem;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.entity.User;
import org.modelmapper.convention.MatchingStrategies;

@SpringBootApplication
public class LuckySystemBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckySystemBackEndApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		modelMapper.typeMap(User.class, UserDto.class).addMappings(mapper -> {
			mapper.map(src -> src.getEmployee() != null ? src.getEmployee().getEmployee_id() : null,
					UserDto::setEmployee);
		});
		return modelMapper;
	}

}
