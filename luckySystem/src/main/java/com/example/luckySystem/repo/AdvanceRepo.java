package com.example.luckySystem.repo;


import com.example.luckySystem.entity.EmployeeAdvanceSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdvanceRepo extends JpaRepository<EmployeeAdvanceSalary,Long> {

    @Query(value= "select * from employeeadvancesalary where advance_salary_id= ?1",nativeQuery = true)
    EmployeeAdvanceSalary getAdvanceByID(String advanceID);
}
