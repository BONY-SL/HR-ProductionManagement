package com.example.luckySystem.repo;

import com.example.luckySystem.entity.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepo extends JpaRepository<EmployeeLeave,Long> {
}
