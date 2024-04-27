package com.example.luckySystem.mappers;

import com.example.luckySystem.dto.user.SignUpDto;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {


    private final EmployeeRepo employeeRepo;

    @Autowired
    public UserMapperImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Named("mapEmployeeToString")
    public String mapEmployeeToString(Employee employee) {
        if (employee == null) {
            return null;
        }
        return employee.getEmployee_id();
    }


    @Named("stringToEmployee")
    public Employee stringToEmployee(String employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeRepo.findById(employeeId).orElse(null);
    }

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        } else {
            UserDto.UserDtoBuilder userDto = UserDto.builder();
            userDto.employee(this.mapEmployeeToString(user.getEmployee()));
            userDto.id(user.getId());
            userDto.username(user.getUsername());
            userDto.password(user.getPassword());
            userDto.email(user.getEmail());
            userDto.contact(user.getContact());
            userDto.roles(user.getRoles());
            return userDto.build();
        }
    }

    public User signUpToUser(SignUpDto signUpDto) {
        if (signUpDto == null) {
            return null;
        } else {
            User.UserBuilder user = User.builder();
            user.employee(this.stringToEmployee(signUpDto.employee()));
            user.username(signUpDto.username());
            user.email(signUpDto.email());
            user.contact(signUpDto.contact());
            return user.build();
        }
    }
}