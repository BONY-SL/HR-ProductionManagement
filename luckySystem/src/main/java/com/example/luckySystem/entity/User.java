package com.example.luckySystem.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",length = 8,unique = true,nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    @Size(max = 512)
    private String password;

    @Column(name="email",length = 100,nullable = false,unique = true)
    @Email
    private String  email;

    @Column(name="contact",length = 10,nullable = false)
    private String contact;

    @Column(name="role",length = 20,nullable = false)
    private String  roles ;


}
