package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Deduction;
import com.example.luckySystem.entity.EmployeeDailyPayRoll;
import com.example.luckySystem.entity.EmployeeMonthlySalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MonthlySalaryRepo extends JpaRepository<EmployeeMonthlySalary,Long> {

    @Query(value = "SELECT * FROM employeemonthlysalary WHERE emp_id = :empId AND MONTH(date) = :month AND YEAR(date) = :year", nativeQuery = true)
    EmployeeMonthlySalary findAllByByMonthYearAndEmpId(@Param("empId") String empId, @Param("month") int month, @Param("year") int year);


    @Query(value = "SELECT * FROM employeemonthlysalary WHERE  MONTH(date) = :month AND YEAR(date) = :year", nativeQuery = true)
    List<EmployeeMonthlySalary> EPFrepoart(@Param("month") int month, @Param("year") int year);



}
