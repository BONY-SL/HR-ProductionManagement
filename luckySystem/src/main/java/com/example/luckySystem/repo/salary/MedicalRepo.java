package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.EmployeeMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRepo extends JpaRepository<EmployeeMedical,Long> {
}
