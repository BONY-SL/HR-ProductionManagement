package com.example.luckySystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity

public class SecurityConfig {


    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/hrandproduction/userlogin", "/hrandproduction/userregister","/hrandproduction/adddailyemptybottles","/hrandproduction/addDailyDamages","/hrandproduction/adddailyfinishedmilk","/hrandproduction/addNewAgent","/hrandproduction/addnewpurchasednewBottles","/hrandproduction/addLording").permitAll()
                        .requestMatchers(HttpMethod.GET,"/hrandproduction/getEmptyBottle","/hrandproduction/getemployeeDamageBottle","/hrandproduction/getDailyFinishedMilkBottle","/hrandproduction/getallAgentDetails","/hrandproduction/getAllLoading").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/hrandproduction/deleteuser").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/hrandproduction/updateEmptyBottle","/hrandproduction/updatedamageBottle","/hrandproduction/updatefinishedMilk","/hrandproduction/updateAgentDetails","/hrandproduction/updateLording").permitAll()
                        .requestMatchers(HttpMethod.PATCH,"/hrandproduction/deleteAgentDetails/{agentId}","/hrandproduction/undoDeleteAgentDetails/{agentId}").permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
    }
}