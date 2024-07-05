package com.example.luckySystem.repo.bottles;
import com.example.luckySystem.entity.CompanyBottleStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyBottleStockRepository extends JpaRepository<CompanyBottleStock, Long> {
    Optional<CompanyBottleStock> findTopByOrderByDateDesc();
}
