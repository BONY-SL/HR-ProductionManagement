package com.example.luckySystem.repo.user;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    //Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByEmployee(Employee  employeeID);

    Optional<User> findByEmployeeAndEmail(Employee  employeeID, String email);

    List<User> findAllByDeleteReasonIsNull();

}
