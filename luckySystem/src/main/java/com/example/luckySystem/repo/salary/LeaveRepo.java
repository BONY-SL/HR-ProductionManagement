package com.example.luckySystem.repo.salary;
import com.example.luckySystem.dto.employee.LeaveChartDTO;
import com.example.luckySystem.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface LeaveRepo extends JpaRepository<EmployeeLeave,Long> {

    @Query("SELECT COUNT(l) FROM EmployeeLeave l WHERE l.emp_id = :employee AND l.status = :status")
    long countByEmpIdAndStatus(Employee  employee, String status);

   // @Query(value = "SELECT * FROM employeeleave WHERE emp_id = :empid", nativeQuery = true)
    //EmployeeLeave getleaveybyid(@Param("empid") String empid);

    @Query("SELECT g FROM EmployeeLeave g WHERE g.emp_id.employee_id = :empId AND g.start_time BETWEEN :startDate AND :endDate ORDER BY g.end_time ASC")
    List<EmployeeLeave> findByEmp_idAAndStart_timeAndEnd_timeOOrderByStart_timeAsc(
            @Param("empId") String  empId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM employeeleave WHERE emp_id = :empId AND MONTH(start_time) = :month AND YEAR(start_time) = :year", nativeQuery = true)
    List<EmployeeLeave> findAllleave(@Param("empId") String empId, @Param("month") int month, @Param("year") int year);
}
