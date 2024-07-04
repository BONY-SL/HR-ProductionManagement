package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.BasicSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasicSalaryRepo extends JpaRepository<BasicSalary,Long> {


    @Query(value= "select * from basicsalary where basic_salary_id= ?1",nativeQuery = true)
    BasicSalary getBasicSalariesByID(String salaryID);


    @Query(value = "SELECT * FROM basicsalary WHERE job_role = :jobRole AND department_name = :departmentName AND section_name=:sectionName", nativeQuery = true)
    BasicSalary findByCustomQuery(@Param("jobRole") String jobRole, @Param("departmentName") String departmentName, @Param("sectionName") String sectionName);



}
