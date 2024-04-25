package com.example.luckySystem.repo;


import com.example.luckySystem.entity.EmployeeLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<EmployeeLoan,Long> {
}
