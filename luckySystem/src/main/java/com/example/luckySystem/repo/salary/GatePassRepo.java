package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeGatePass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface GatePassRepo extends JpaRepository<EmployeeGatePass,Long> {

    @Query("SELECT COUNT(l) FROM EmployeeGatePass l WHERE l.emp_id = :employee AND l.status = :status")
    long countByEmpIdAndStatus(Employee employee, String status);

    @Query("SELECT g FROM EmployeeGatePass g WHERE g.emp_id.employee_id = :empId AND g.date BETWEEN :startDate AND :endDate ORDER BY g.date ASC")
    List<EmployeeGatePass> findByEmp_idAndDateBetweenOrderByDateAsc(
            @Param("empId") String  empId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

}
