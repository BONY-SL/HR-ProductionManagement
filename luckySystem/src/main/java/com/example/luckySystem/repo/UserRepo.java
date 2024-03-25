package com.example.luckySystem.repo;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    //Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByEmployee(Employee employeeID);

    //Boolean findById(long id);
}
