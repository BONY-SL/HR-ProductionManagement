package com.example.luckySystem.service.user;

import com.example.luckySystem.dto.user.CredentialsDto;
import com.example.luckySystem.dto.user.SignUpDto;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.mappers.UserMapper;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.user.UserRepo;
import com.example.luckySystem.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@ComponentScan
@Component
@Repository
public class UserService {

    private final UserRepo userRepository;

    private final EmployeeRepo employeeRepo;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final ModelMapper modelMapper;

    private final EmployeeService employeeService;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.username())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.username());
        Boolean email=userRepository.existsByEmail(userDto.email());

        //Check The Username Already Have In the User Table
        if (optionalUser.isPresent()) {
            throw new AppException("Login name already exists", HttpStatus.BAD_REQUEST);
        }if(email){
            throw new AppException("Login email already exists",HttpStatus.BAD_REQUEST);
        }
        if(!employeeService.employeeExists(userDto.employeeid())) {

            throw new AppException("Employee not found with ID: ", HttpStatus.BAD_REQUEST);

        }
            User user = modelMapper.map(userDto,User.class);

            Employee employee=employeeRepo.findById(userDto.employeeid()).orElseThrow(() -> new AppException("Employee not found",HttpStatus.BAD_REQUEST));
            user.setEmployee(employee);
            Boolean id=userRepository.existsByEmployee(employee);

            if(id){
                throw new AppException("The Entered Employee ID Already Use to User Acount", HttpStatus.BAD_REQUEST);
            }

            user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
            user.setRoles(userDto.role());
            user.setContact(userDto.contact());
            user.setEmail(userDto.email());
            user.setUsername(userDto.username());
            User savedUser = userRepository.save(user);


            return modelMapper.map(savedUser,UserDto.class);
    }
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

}
