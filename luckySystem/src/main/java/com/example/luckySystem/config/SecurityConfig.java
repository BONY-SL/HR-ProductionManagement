package com.example.luckySystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Properties;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration

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
                        .requestMatchers(HttpMethod.POST, "/hrandproduction/userlogin", "/hrandproduction/userregister","/hrandproduction/adddailyemptybottles","/hrandproduction/addDailyDamages","/hrandproduction/adddailyfinishedmilk","/hrandproduction/addNewAgent","/hrandproduction/addnewpurchasednewBottles","/hrandproduction/addLording","/hrandproduction/addEmployee","/hrandproduction/addAdvance","/hrandproduction/addAllowance","/hrandproduction/addAttendance"
                                                        ,"/hrandproduction/addSalary","/hrandproduction/addDeduction","/hrandproduction/addGatepass","/hrandproduction/addLeave","/hrandproduction/addLoan","/hrandproduction/addMedical","/hrandproduction/createNewUser","/hrandproduction/sendmailToUser","/hrandproduction/addNewIssue/{issue}","/hrandproduction/addDailyIssuesemployee").permitAll()
                        .requestMatchers(HttpMethod.GET,"/hrandproduction/getEmptyBottle","/hrandproduction/getemployeeDamageBottle","/hrandproduction/getDailyFinishedMilkBottle","/hrandproduction/getallAgentDetails","/hrandproduction/getAllLoading","/hrandproduction/getEmployee","/hrandproduction/employeeCountByDepartment","/hrandproduction/totalCount","/hrandproduction/getEmployeeByID/{employeeID}","/hrandproduction/getAdvance","/hrandproduction/getAdvanceByID/{advanceID}","/hrandproduction/getAllowance"
                                                      ,"/hrandproduction/getAllowanceByID/{allowanceID}","hrandproduction/getAttendance","hrandproduction/employeeCount","hrandproduction/getSalary","hrandproduction/getSalaryByID/{salaryID}","hrandproduction/getDeduction","hrandproduction/getDeductionByID/{deductionID}","hrandproduction/getGatepass","hrandproduction/getLeave","hrandproduction/getLoan","hrandproduction/getLoanByID/{loanID}","hrandproduction/getmedical","/hrandproduction/getallUsers",
                                "/hrandproduction/getIssueDetails","/hrandproduction/gettAllIssueByEmployee").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/hrandproduction/deleteuser","/hrandproduction/deleteEmployee","/hrandproduction/deleteAdvance","/hrandproduction/deleteAllowance","/hrandproduction/deleteSalary","/hrandproduction/deleteDeduction","/hrandproduction/deleteLoan").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/hrandproduction/updateEmptyBottle","/hrandproduction/updatedamageBottle","/hrandproduction/updatefinishedMilk","/hrandproduction/updateAgentDetails","/hrandproduction/updateLording","/hrandproduction/updateEmployee","/hrandproduction/updateAdvance","/hrandproduction/updateAllowance","/hrandproduction/updateSalary","/hrandproduction/updateDeduction","/hrandproduction/updateLone","/hrandproduction/updateIssue").permitAll()
                        .requestMatchers(HttpMethod.PATCH,"/hrandproduction/deleteAgentDetails/{agentId}","/hrandproduction/undoDeleteAgentDetails/{agentId}").permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
    }


}
