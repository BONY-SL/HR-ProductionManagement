package com.example.luckySystem.security.JWTserviceToUser;

import com.example.luckySystem.entity.User;
import com.example.luckySystem.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsSer implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetail.build(user);
    }
}
