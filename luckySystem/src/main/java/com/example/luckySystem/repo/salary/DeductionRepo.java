package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeductionRepo extends JpaRepository<Deduction,Long> {

    @Query(value="select * from deduction where deduction_id=?1",nativeQuery = true)
    Deduction getDeductionByDeductionID(String deductionId);
}
