package com.example.luckySystem.mappers;

import com.example.luckySystem.dto.SignUpDto;
import com.example.luckySystem.dto.UserDto;
import com.example.luckySystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password",ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
