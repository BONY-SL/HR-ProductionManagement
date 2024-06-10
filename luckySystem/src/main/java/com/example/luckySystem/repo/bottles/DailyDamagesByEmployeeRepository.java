package com.example.luckySystem.repo.bottles;
import com.example.luckySystem.entity.DailyDamageBottleByEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyDamagesByEmployeeRepository extends JpaRepository<DailyDamageBottleByEmployee, Long> {

    @Query("SELECT d FROM DailyDamageBottleByEmployee d WHERE d.date >= :startDate")
    List<DailyDamageBottleByEmployee> findAllFromLastThreeMonths(@Param("startDate") Date startDate);
}
