package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeGatePass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GatePassRepo extends JpaRepository<EmployeeGatePass,Long> {

    @Query("SELECT COUNT(l) FROM EmployeeGatePass l WHERE l.emp_id = :employee AND l.status = :status")
    long countByEmpIdAndStatus(Employee employee, String status);

}
