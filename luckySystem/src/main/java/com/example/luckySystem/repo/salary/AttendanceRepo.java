package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<EmployeeAttendance,Long> {


    @Query("SELECT COUNT(DISTINCT a.emp_id) AS employee_count FROM EmployeeAttendance a WHERE a.date = :date")
    int countDistinctEmployeesByDate(@Param("date") String date);

    @Query(value = "SELECT * FROM EmployeeAttendance WHERE emp_id = :empId AND date = :dateStr", nativeQuery = true)
    EmployeeAttendance findByEmpIdAndDateNative(@Param("empId") String empId, @Param("dateStr") String dateStr);

    @Query("SELECT g FROM EmployeeAttendance g WHERE g.emp_id.employee_id = :empId AND g.date BETWEEN :startDate AND :endDate ORDER BY g.date ASC")
    List<EmployeeAttendance> findByEmp_idAndDateBetweenOrderByDateAsc(
            @Param("empId") String  empId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}