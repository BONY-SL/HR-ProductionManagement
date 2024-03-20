package com.example.luckySystem.controller;


import com.example.luckySystem.entity.EnumRole;
import com.example.luckySystem.entity.Role;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.repository.RoleRepo;
import com.example.luckySystem.repository.UserRepo;
import com.example.luckySystem.security.JWTsecur.JwtResorce;
import com.example.luckySystem.security.JWTserviceToUser.UserDetail;
import com.example.luckySystem.userLoadModules.request.LoginRe;
import com.example.luckySystem.userLoadModules.request.SignUpRe;
import com.example.luckySystem.userLoadModules.response.JwtRes;
import com.example.luckySystem.userLoadModules.response.MessageRes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserSignInAndSignUp {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtResorce jwtResorce;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/loginuser")
    synchronized public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRe loginRe){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRe.getUsername(),loginRe.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtResorce.generateJwtToken(authentication);

        UserDetail userDetail= (UserDetail) authentication.getPrincipal();

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtRes(jwt,userDetail.getId(),userDetail.getUsername(),userDetail.getEmail(),roles));


    }

    @PostMapping("/regiteruser")
    synchronized public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRe signUpRe){

        if(userRepo.existsByUsername(signUpRe.getUsername())){
            return ResponseEntity.badRequest().body(new MessageRes("The User is Already taken"));
        }
        if(userRepo.existsByEmail(signUpRe.getEmail())){
            return ResponseEntity.badRequest().body(new MessageRes("The Email is Already taken"));

        }

        User user=new User(signUpRe.getUsername(),passwordEncoder.encode(signUpRe.getPassword()),signUpRe.getEmail(),signUpRe.getContact());

        Set<String> stringrole=signUpRe.getRole();

        Set<Role> roles=new HashSet<>();

        stringrole.forEach(role->{
            switch (role){
                case "HR_Manager":
                    Role hrmanager=roleRepo.findByName(EnumRole.HR_Manager)
                            .orElseThrow(()->new RuntimeException("Role is Not Found"));
                    roles.add(hrmanager);
                    break;
                case "Accountant":
                    Role acountant=roleRepo.findByName(EnumRole.Accountant)
                            .orElseThrow(()->new RuntimeException("Role is Not Found"));
                    roles.add(acountant);
                    break;
                case "Production_Manager":
                    Role productionmanager=roleRepo.findByName(EnumRole.Production_Manager)
                            .orElseThrow(()->new RuntimeException("Role is Not Found"));
                    roles.add(productionmanager);
                    break;
                case "Store_Keeper":
                    Role storekeeper=roleRepo.findByName(EnumRole.Store_Keeper)
                            .orElseThrow(()->new RuntimeException("Role is Not Found"));
                    roles.add(storekeeper);
                    break;
                default:
                    Role systemadminRole = roleRepo.findByName(null)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(systemadminRole);
            }
        });

        user.setRoles(roles);
        userRepo.save(user);

        return ResponseEntity.ok(new MessageRes("User Register SuccessFully....."));
    }
}