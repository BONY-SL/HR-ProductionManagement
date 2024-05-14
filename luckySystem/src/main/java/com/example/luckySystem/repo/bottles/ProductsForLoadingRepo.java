package com.example.luckySystem.repo.bottles;


import com.example.luckySystem.entity.GoodProductsForLoading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductsForLoadingRepo extends JpaRepository<GoodProductsForLoading,Long> {


    @Query("SELECT g FROM GoodProductsForLoading g WHERE g.ag_id.agent_id = :agentId AND g.submit_date BETWEEN :startDate AND :endDate ORDER BY g.submit_date ASC")
    List<GoodProductsForLoading> findByAgIdAndSubmitDateBetweenOrderBySubmitDateAsc(
            @Param("agentId") Long agentId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

}
