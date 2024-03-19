package com.example.luckySystem.security.WebsecurityConfig;
import com.example.luckySystem.security.JWTsecur.SecurityEntryPoint;
import com.example.luckySystem.security.JWTsecur.TokenFilters;
import com.example.luckySystem.security.JWTserviceToUser.UserDetailsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfiger{

    @Autowired
    UserDetailsSer userDetailsSer;
    @Autowired
    private SecurityEntryPoint securityEntryPoint;

    @Bean
    public TokenFilters authenticationJwtTokenFilter() {
        return new TokenFilters();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsSer);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration cfg = new CorsConfiguration();
                    cfg.setAllowedMethods(Collections.singletonList("*"));
                    cfg.setAllowCredentials(true);
                    cfg.setAllowedHeaders(Collections.singletonList("*"));
                    cfg.setExposedHeaders(Arrays.asList("Authorization"));
                    cfg.setMaxAge(3600L);
                    return cfg;
                }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new TokenFilters(), BasicAuthenticationFilter.class);
        return http.build();
    }
}

