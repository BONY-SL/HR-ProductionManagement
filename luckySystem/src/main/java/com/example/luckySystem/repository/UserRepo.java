package com.example.luckySystem.repository;

import com.example.luckySystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

   Optional<User> findByUser_name(String username);

   Boolean existsByUser_name(String username);

   Boolean existsByEmail(String email);

}
