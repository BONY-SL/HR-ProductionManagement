package com.example.luckySystem.service.user;
import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.user.CredentialsDto;
import com.example.luckySystem.dto.user.SignUpDto;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.entity.Agent;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.mappers.UserMapper;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.user.UserRepo;
import com.example.luckySystem.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Component
@Repository
@ComponentScan
public class UserService {

    private final UserRepo userRepository;

    private final EmployeeRepo employeeRepo;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final EmployeeService employeeService;
    

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.username())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            UserDto userDto = userMapper.toUserDto(user);
            if (user.getEmployee() != null) {
                userDto.setEmployee(user.getEmployee().getEmployee_id()); // Set whatever Employee information you need
            }
            return userDto;
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public void register(SignUpDto userDto) {

        System.out.println("2"+userDto);
        Optional<User> optionalUser = userRepository.findByUsername(userDto.username());
        Boolean emailExists = userRepository.existsByEmail(userDto.email());

        if (optionalUser.isPresent()) {
            throw new AppException("Login name already exists", HttpStatus.BAD_REQUEST);
        }
        if (emailExists) {
            throw new AppException("Login email already exists", HttpStatus.BAD_REQUEST);
        }

        if (!employeeService.employeeExists(userDto.employee())) {
            throw new AppException("Employee not found with ID: " + userDto.employee(), HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);


        Employee employee = employeeRepo.findById(userDto.employee())
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.BAD_REQUEST));
        user.setEmployee(employee);

        if (userRepository.existsByEmployee(employee)) {
            throw new AppException("The entered Employee ID is already associated with another user", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
        user.setRoles(userDto.role());
        user.setContact(userDto.contact());
        user.setEmail(userDto.email());
        userRepository.save(user);
        System.out.println("save");
        System.out.println(user);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        Employee employee=user.getEmployee();
        UserDto userDto=userMapper.toUserDto(user);

        if (employee != null) {
            userDto.setEmployee(employee.getEmployee_id());
        }

        return userDto;
    }
    public void updateUserDetails(UserDto dto) {

        User user = userRepository.findById(dto.getId()).orElseThrow();
        user.setId(dto.getId());
        userRepository.save(user);
    }

    public void deleteUserDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Agent not found"));
        userRepository.save(user);
    }

    public List<UserDto> getallUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertUserEntityToDTO).collect(Collectors.toList());
    }

    private UserDto convertUserEntityToDTO(User unit) {

        return new UserDto(unit.getId(),unit.getUsername(),unit.getPassword(),unit.getEmail(),unit.getContact(),unit.getRoles(),unit.getEmployee().getEmployee_id(),null);
    }

}