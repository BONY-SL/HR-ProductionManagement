package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLeave;
import com.example.luckySystem.entity.EmployeeLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveRepo extends JpaRepository<EmployeeLeave,Long> {

    @Query("SELECT COUNT(l) FROM EmployeeLeave l WHERE l.emp_id = :employee AND l.status = :status")
    long countByEmpIdAndStatus(Employee  employee, String status);

    @Query(value = "SELECT * FROM employeeleave WHERE emp_id = :empid", nativeQuery = true)
    EmployeeLeave getleaveybyid(@Param("empid") String empid);
}
