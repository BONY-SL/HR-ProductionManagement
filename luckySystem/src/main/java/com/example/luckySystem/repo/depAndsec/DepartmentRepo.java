package com.example.luckySystem.repo.depAndsec;


import com.example.luckySystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,String> {
}
