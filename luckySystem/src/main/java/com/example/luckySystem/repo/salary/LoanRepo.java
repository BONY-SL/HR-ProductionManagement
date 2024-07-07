package com.example.luckySystem.repo.salary;



import com.example.luckySystem.entity.Allowances;
import com.example.luckySystem.entity.EmployeeLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepo extends JpaRepository<EmployeeLoan,Long> {

    @Query(value="select * from employeelone where loan_id=?1",nativeQuery = true)
    EmployeeLoan getLoanById(String emp_id);

    @Query(value = "SELECT * FROM employeelone WHERE emp_id = :empid", nativeQuery = true)
    EmployeeLoan getloanbyid(@Param("empid") String empid);


}
