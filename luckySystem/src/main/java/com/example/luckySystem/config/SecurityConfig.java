package com.example.luckySystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@Configuration
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
                        .requestMatchers(HttpMethod.POST, "/userlogin", "/serregister","/addSalary","/addDeduction","/addAllowance","/addEmployee").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/deleteSalary","/deleteDeduction","/deleteAllowance","/deleteEmployee","/deleteLoan","/deleteAdvance").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/updateSalary","/updateDeduction","/updateAllowance","/updateuserDetails","/updateEmployee","/updateLone","/updateAdvance").permitAll()
                        .requestMatchers(HttpMethod.GET, "/getSalary", "/getSalaryByID/{salaryID}","/getSalaryBydepartment/{departmentName}","/getLoan").permitAll()
                        .requestMatchers(HttpMethod.GET, "/getDeduction","/getAllowance","/getAllowanceByID/{allowanceID}","/getDeductionByID/{deductionID}","/employeeCount","/getGatepass","/getEmployeeByID/{employeeID}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/getuserdetailsByID/{userId}","/getEmployee","/getmedical","/getAttendance","/getLeave","/getAdvance","/employeeCountByDepartment","/totalCount","/getLoanByID/{loanID}","/getAdvanceByID/{advanceID}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/addAdvance","/addAttendance","/addLeave","/addLoan","/addMedical","/addGatepass").permitAll()


                        .anyRequest().authenticated())
        ;
        return http.build();
    }
}
