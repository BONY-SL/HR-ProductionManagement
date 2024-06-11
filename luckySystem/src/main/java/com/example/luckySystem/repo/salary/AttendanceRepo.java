package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepo extends JpaRepository<EmployeeAttendance,Long> {

    @Query("SELECT COUNT(DISTINCT a.emp_id) AS employee_count FROM EmployeeAttendance a WHERE a.date = :date")
    int countDistinctEmployeesByDate(@Param("date") String date);
}
