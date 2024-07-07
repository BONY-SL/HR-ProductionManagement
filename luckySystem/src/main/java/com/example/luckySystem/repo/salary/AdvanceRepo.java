package com.example.luckySystem.repo.salary;


import com.example.luckySystem.entity.EmployeeAdvanceSalary;
import com.example.luckySystem.entity.EmployeeLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdvanceRepo extends JpaRepository<EmployeeAdvanceSalary,Long> {

    @Query(value= "select * from employeeadvancesalary where advance_salary_id= ?1",nativeQuery = true)
    EmployeeAdvanceSalary getAdvanceByID(String advanceID);

    @Query(value = "SELECT * FROM employeeadvancesalary WHERE emp_id = :empid", nativeQuery = true)
    EmployeeAdvanceSalary getAdvancebyid(@Param("empid") String empid);
}
