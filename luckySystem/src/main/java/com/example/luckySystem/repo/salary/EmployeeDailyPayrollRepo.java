package com.example.luckySystem.repo.salary;


import com.example.luckySystem.entity.EmployeeDailyPayRoll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDailyPayrollRepo extends JpaRepository<EmployeeDailyPayRoll,Long> {
}
