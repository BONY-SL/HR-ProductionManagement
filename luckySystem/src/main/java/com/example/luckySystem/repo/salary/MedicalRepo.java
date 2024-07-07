package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLeave;
import com.example.luckySystem.entity.EmployeeMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicalRepo extends JpaRepository<EmployeeMedical,Long> {

    @Query("SELECT COUNT(l) FROM EmployeeMedical l WHERE l.employee = :employee AND l.medical_status = :status")
    long countByEmpIdAndStatus(Employee employee, String status);

    @Query(value = "SELECT * FROM employeemedical WHERE emp_id = :empid", nativeQuery = true)
    EmployeeMedical getMedicalbyid(@Param("empid") String empid);

}
