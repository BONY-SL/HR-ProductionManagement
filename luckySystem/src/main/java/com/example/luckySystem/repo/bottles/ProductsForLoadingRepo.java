package com.example.luckySystem.repo.bottles;


import com.example.luckySystem.dto.agent.AgentReportDTO;
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


    @Query("SELECT new com.example.luckySystem.dto.agent.AgentReportDTO(a.agent_name, a.agency_name, SUM(g.amount)) " +
            "FROM GoodProductsForLoading g JOIN g.ag_id a " +
            "WHERE FUNCTION('DATE', g.submit_date) = :date " +
            "GROUP BY a.agent_id, a.agent_name, a.agency_name")
    List<AgentReportDTO> findDailyReportByDate(@Param("date") Date date);


    @Query("SELECT new com.example.luckySystem.dto.agent.AgentReportDTO(a.agent_name, a.agency_name, SUM(g.amount)) " +
            "FROM GoodProductsForLoading g JOIN g.ag_id a " +
            "WHERE g.submit_date BETWEEN :fromDate AND :toDate " +
            "GROUP BY a.agent_id, a.agent_name, a.agency_name")
    List<AgentReportDTO> findReportByDateRange(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("SELECT new com.example.luckySystem.dto.agent.AgentReportDTO(a.agent_name, a.agency_name, SUM(g.amount)) " +
            "FROM GoodProductsForLoading g JOIN g.ag_id a " +
            "WHERE FUNCTION('MONTH', g.submit_date) = :month AND FUNCTION('YEAR', g.submit_date) = :year " +
            "GROUP BY a.agent_id, a.agent_name, a.agency_name")
    List<AgentReportDTO> findReportByMonth(@Param("month") int month, @Param("year") int year);

}
