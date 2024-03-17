package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name="user")
public class User {

    @Id
    @Column(name="user_name",length = 8,unique = true,nullable = false)
    private String UserName;

    @Column(name="user_password",length = 10,nullable = false)
    private String password;

    @Column(name="login_status",nullable = false)
    private boolean status;

    @OneToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name="email",length = 100,nullable = false,unique = true)
    private String  Email;

    @Column(name="contact_number",length = 10,nullable = false)
    private String  ContactNumber;

}
