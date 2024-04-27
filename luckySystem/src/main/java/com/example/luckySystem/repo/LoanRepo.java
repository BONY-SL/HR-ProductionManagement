package com.example.luckySystem.repo;



import com.example.luckySystem.entity.EmployeeLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepo extends JpaRepository<EmployeeLoan,Long> {

    @Query(value="select * from employeelone where loan_id=?1",nativeQuery = true)
    EmployeeLoan getLoanById(String emp_id);

}
