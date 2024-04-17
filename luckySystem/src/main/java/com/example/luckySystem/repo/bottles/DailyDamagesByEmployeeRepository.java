package com.example.luckySystem.repo.bottles;
import com.example.luckySystem.entity.DailyDamageBottleByEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyDamagesByEmployeeRepository extends JpaRepository<DailyDamageBottleByEmployee, Long> {

}
