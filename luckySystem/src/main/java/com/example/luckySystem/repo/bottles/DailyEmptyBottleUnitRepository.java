package com.example.luckySystem.repo.bottles;
import com.example.luckySystem.entity.DailyEmptyBottleUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyEmptyBottleUnitRepository extends JpaRepository<DailyEmptyBottleUnit, Long> {



    @Query("SELECT d FROM DailyEmptyBottleUnit d WHERE d.submit_date >= :startDate")
    List<DailyEmptyBottleUnit> findAllFromLastThreeMonths(@Param("startDate") Date startDate);
}
