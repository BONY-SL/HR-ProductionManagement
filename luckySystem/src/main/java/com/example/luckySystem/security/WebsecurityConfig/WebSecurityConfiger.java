package com.example.luckySystem.security.WebsecurityConfig;


import com.example.luckySystem.security.JWTsecur.SecurityEntryPoint;
import com.example.luckySystem.security.JWTsecur.TokenFilter;
import com.example.luckySystem.security.JWTserviceToUser.UserDetailsSer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class WebSecurityConfiger {

    @Autowired
    UserDetailsSer userDetailsSer;

    @Autowired
    private SecurityEntryPoint securityEntryPoint;

    @Bean
    public TokenFilter authenticationJwtTokenFilter(){
        return new TokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
        authprovider.setUserDetailsService(userDetailsSer);
        authprovider.setPasswordEncoder(passwordEncoder());

        return  authprovider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
