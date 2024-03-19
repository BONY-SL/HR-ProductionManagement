package com.example.luckySystem.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name="user",uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="user_name",length = 8,unique = true,nullable = false)
    private String user_name;

    @Column(name="user_password",length = 50,nullable = false)
    @NotBlank
    private String user_password;

    @Column(name="email",length = 100,nullable = false,unique = true)
    @Email
    @NotBlank
    private String  email;

    @Column(name="contact_number",length = 10,nullable = false)
    @NotBlank
    private String  contact_number;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
