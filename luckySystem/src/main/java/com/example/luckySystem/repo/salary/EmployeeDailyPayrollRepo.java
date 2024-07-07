package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.EmployeeDailyPayRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDailyPayrollRepo extends JpaRepository<EmployeeDailyPayRoll, Long> {

    @Query(value = "SELECT * FROM employeedailypayroll WHERE emp_id = :empId AND MONTH(date) = :month AND YEAR(date) = :year", nativeQuery = true)
    List<EmployeeDailyPayRoll> findAllByByMonthYearAndEmpId(@Param("empId") String empId, @Param("month") int month, @Param("year") int year);
}
