package com.example.luckySystem.repo.salary;


import com.example.luckySystem.entity.EmployeeAttendance;
import com.example.luckySystem.entity.EmployeeMonthlySalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MonthlySalaryRepo extends JpaRepository<EmployeeMonthlySalary,Long> {


}
