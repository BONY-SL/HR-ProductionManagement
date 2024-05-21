package com.example.luckySystem.repo.bottles;
import com.example.luckySystem.dto.agent.ProductionReportDTO;
import com.example.luckySystem.entity.DailyFinished;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyFinishedRepostory extends JpaRepository<DailyFinished, Long> {

    @Query("SELECT new com.example.luckySystem.dto.agent.ProductionReportDTO(SUM(d.amount), d.batch_code, d.finished_status) " +
            "FROM DailyFinished d " +
            "WHERE FUNCTION('DATE', d.submit_date) = :submitDate " +
            "GROUP BY d.batch_code, d.finished_status")
    List<ProductionReportDTO> findDailyProductionsByDate(@Param("submitDate") Date submitDate);

    @Query("SELECT new com.example.luckySystem.dto.agent.ProductionReportDTO(SUM(d.amount), d.batch_code, d.finished_status) " +
            "FROM DailyFinished d " +
            "WHERE d.submit_date BETWEEN :fromDate AND :toDate " +
            "GROUP BY d.batch_code, d.finished_status")
    List<ProductionReportDTO> findProductionsByDateRange(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);


    @Query("SELECT new com.example.luckySystem.dto.agent.ProductionReportDTO(SUM(d.amount), d.batch_code, d.finished_status) " +
            "FROM DailyFinished d " +
            "WHERE MONTH(d.submit_date) = :month AND YEAR(d.submit_date) = :year " +
            "GROUP BY d.batch_code, d.finished_status")
    List<ProductionReportDTO> findProductionsByMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT d FROM DailyFinished d WHERE d.submit_date >= :startDate")
    List<DailyFinished> findAllFromLastThreeMonths(@Param("startDate") Date startDate);
}

