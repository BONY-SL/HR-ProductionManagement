package com.example.luckySystem.repository;

import com.example.luckySystem.entity.EnumRole;
import com.example.luckySystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(EnumRole name);
}
