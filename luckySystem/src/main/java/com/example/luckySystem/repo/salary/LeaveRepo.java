package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeaveRepo extends JpaRepository<EmployeeLeave,Long> {

    @Query("SELECT COUNT(l) FROM EmployeeLeave l WHERE l.emp_id = :employee AND l.status = :status")
    long countByEmpIdAndStatus(Employee  employee, String status);
}
