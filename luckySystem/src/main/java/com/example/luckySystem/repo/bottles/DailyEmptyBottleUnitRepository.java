package com.example.luckySystem.repo.bottles;

import com.example.luckySystem.entity.DailyEmptyBottleUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyEmptyBottleUnitRepository extends JpaRepository<DailyEmptyBottleUnit, Long> {
}
