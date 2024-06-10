package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface MedicalRepo extends JpaRepository<EmployeeMedical,Long> {

}
