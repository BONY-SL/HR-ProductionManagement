package com.example.luckySystem.service.user;
import com.example.luckySystem.controller.user.OTPSearailzeble;
import com.example.luckySystem.dto.user.CredentialsDto;
import com.example.luckySystem.dto.user.SignUpDto;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.dto.user.UserUpdateRequestDTO;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.mappers.UserMapper;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.user.UserRepo;
import com.example.luckySystem.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
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

    public void deleteUserDetails(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getallUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertUserEntityToDTO).collect(Collectors.toList());
    }

    private UserDto convertUserEntityToDTO(User unit) {

        return new UserDto(unit.getId(),unit.getUsername(),unit.getPassword(),unit.getEmail(),unit.getContact(),unit.getRoles(),unit.getEmployee().getEmployee_id(),null);
    }

    public boolean  checkUserIDandUserEmail(String userID, String email){


        Employee employee=employeeRepo.findById(userID).orElseThrow(() -> new AppException("Employee Is Not Found", HttpStatus.NOT_FOUND));

        return userRepository.findByEmployeeAndEmail(employee,email).isPresent();


    }

    public void resetPassword(OTPSearailzeble userMailAndOTPSerailzeble, String password) throws AppException {

        Employee employee=employeeRepo.findById(userMailAndOTPSerailzeble.getEmployeeID())
                .orElseThrow(() -> new AppException("Employee Is Not Found", HttpStatus.NOT_FOUND));


        User user=userRepository.findByEmployeeAndEmail(employee,userMailAndOTPSerailzeble.getEmail())
                .orElseThrow(()->new AppException("Invalid Email Address",HttpStatus.NOT_FOUND));

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(password)));
        userRepository.save(user);


    }

    public UserDto updateUserProfile(UserUpdateRequestDTO userUpdateRequestDTO) {

        User user = userRepository.findById(userUpdateRequestDTO.getId())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));


        Optional<User> existingUserWithUsername = userRepository.findByUsername(userUpdateRequestDTO.getUsername());
        if (existingUserWithUsername.isPresent() && !existingUserWithUsername.get().getId().equals(user.getId())) {
            throw new AppException("New username already exists", HttpStatus.BAD_REQUEST);
        }


        Optional<User> existingUserWithEmail = userRepository.findByEmail(userUpdateRequestDTO.getEmail());
        if (existingUserWithEmail.isPresent() && !existingUserWithEmail.get().getId().equals(user.getId())) {
            throw new AppException("New email already exists", HttpStatus.BAD_REQUEST);
        }

        user.setUsername(userUpdateRequestDTO.getUsername());
        user.setEmail(userUpdateRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userUpdateRequestDTO.getPassword())));
        user.setContact(userUpdateRequestDTO.getContact());
        userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setContact(user.getContact());
        userDto.setRoles(user.getRoles());
        userDto.setEmployee(user.getEmployee().getEmployee_id());

        return userDto;
    }

}