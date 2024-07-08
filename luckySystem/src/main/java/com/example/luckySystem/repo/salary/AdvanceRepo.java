package com.example.luckySystem.repo.salary;


import com.example.luckySystem.entity.EmployeeAdvanceSalary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdvanceRepo extends JpaRepository<EmployeeAdvanceSalary,Long> {

    @Query(value= "select * from employeeadvancesalary where advance_salary_id= ?1",nativeQuery = true)
    EmployeeAdvanceSalary getAdvanceByID(String advanceID);

    @Query(value = "SELECT * FROM employeeadvancesalary WHERE emp_id = :empid", nativeQuery = true)
    EmployeeAdvanceSalary getAdvancebyid(@Param("empid") String empid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employeeadvancesalary SET amount = :advanceAmount WHERE advance_salary_id = :advanceId", nativeQuery = true)
    int updateAdvanceAmount(@Param("advanceAmount") double advanceAmount, @Param("advanceId") Long advanceId);
}
