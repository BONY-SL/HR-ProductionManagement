package com.example.luckySystem.repo;

import com.example.luckySystem.entity.EmployeeAdvanceSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvanceRepo extends JpaRepository<EmployeeAdvanceSalary,Long> {
}
