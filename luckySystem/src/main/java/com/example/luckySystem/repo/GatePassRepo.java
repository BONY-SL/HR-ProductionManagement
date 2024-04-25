package com.example.luckySystem.repo;

import com.example.luckySystem.entity.EmployeeGatePass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatePassRepo extends JpaRepository<EmployeeGatePass,Long> {
}
