package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Data

@Component
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRole name;

    public Role() {

    }

    public Role(EnumRole name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(EnumRole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public EnumRole getName() {
        return name;
    }
}
