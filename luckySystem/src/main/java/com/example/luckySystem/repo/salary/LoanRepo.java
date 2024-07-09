package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.EmployeeLoan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends JpaRepository<EmployeeLoan,Long> {

    @Query(value="select * from employeelone where loan_id=?1",nativeQuery = true)
    EmployeeLoan getLoanById(String emp_id);

    @Query(value = "SELECT * FROM employeelone WHERE emp_id = :empid", nativeQuery = true)
    EmployeeLoan getloanbyid(@Param("empid") String empid);


    @Transactional
    @Modifying
    @Query(value = "UPDATE employeelone SET loan_amount = :loanAmount WHERE loan_id = :loanId", nativeQuery = true)
    int updateLoanAmount(@Param("loanAmount") double loanAmount, @Param("loanId") Long loanId);


}



