package com.example.luckySystem.mappers;

import com.example.luckySystem.dto.user.SignUpDto;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "employee", target = "employee", qualifiedByName = "employeeToString")
    UserDto toUserDto(User user);


    EmployeeRepo employeeRepo = null;

    @Mapping(source = "employee", target = "employee", qualifiedByName = "stringToEmployee")
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

    @Named("employeeToString")
    default String mapEmployeeToString(Employee employee) {
        return employee == null ? null : employee.getEmployee_id();
    }

    @Named("stringToEmployee")
     default Employee stringToEmployee(String employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeRepo.findById(employeeId).orElse(null);
    }
}
