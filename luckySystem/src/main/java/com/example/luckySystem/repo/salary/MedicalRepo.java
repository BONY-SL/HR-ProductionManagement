package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLeave;
import com.example.luckySystem.entity.EmployeeAttendance;
import com.example.luckySystem.entity.EmployeeMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface MedicalRepo extends JpaRepository<EmployeeMedical,Long> {

    @Query("SELECT COUNT(l) FROM EmployeeMedical l WHERE l.employee = :employee AND l.medical_status = :status")
    long countByEmpIdAndStatus(Employee employee, String status);

    @Query(value = "SELECT * FROM employeemedical WHERE emp_id = :empid", nativeQuery = true)
    EmployeeMedical getMedicalbyid(@Param("empid") String empid);


    @Query("SELECT g FROM EmployeeMedical g WHERE g.employee.employee_id = :empId AND g.submit_date BETWEEN :startDate AND :endDate ORDER BY g.submit_date ASC")
    List<EmployeeMedical> findByEmployeeAndSubmit_dateBetweenOrderBySubmit_dateAsc(
            @Param("empId") String  empId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
