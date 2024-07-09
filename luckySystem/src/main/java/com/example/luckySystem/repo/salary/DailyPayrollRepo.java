package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.DailyPayRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPayrollRepo extends JpaRepository<DailyPayRoll,Long> {
}
