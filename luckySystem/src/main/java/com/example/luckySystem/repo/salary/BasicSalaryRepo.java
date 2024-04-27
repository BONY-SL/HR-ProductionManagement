package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.BasicSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasicSalaryRepo extends JpaRepository<BasicSalary,Long> {


    @Query(value= "select * from basicsalary where basic_salary_id= ?1",nativeQuery = true)
    BasicSalary getBasicSalariesByID(String salaryID);


}
