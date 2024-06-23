package com.example.luckySystem.repo.issue;

import com.example.luckySystem.dto.issue.GetMonthlyIssueDTO;
import com.example.luckySystem.dto.issue.IssueReportDTO;
import com.example.luckySystem.entity.DailyProductionIssuesByEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface DailyIssueRepo extends JpaRepository<DailyProductionIssuesByEmployee,Long> {



    /*
    * Manage this Repo For Issue Reports
    *
    * */

    @Query("SELECT new com.example.luckySystem.dto.issue.GetMonthlyIssueDTO(d.issue_name, COUNT(d.issue_name), SUM(d.damage_amount)) " +
            "FROM DailyProductionIssuesByEmployee d " +
            "WHERE MONTH(d.submit_date) = :month AND YEAR(d.submit_date) = :year " +
            "GROUP BY d.issue_name")
    List<GetMonthlyIssueDTO> findIssuesGroupedByIssueName(@Param("month") int month, @Param("year") int year);


    @Query("SELECT new com.example.luckySystem.dto.issue.IssueReportDTO(SUM(d.damage_amount), d.issue_name) " +
            "FROM DailyProductionIssuesByEmployee d " +
            "WHERE FUNCTION('DATE', d.submit_date) = :submitDate " +
            "GROUP BY d.issue_name")
    List<IssueReportDTO> findDailyIssuesByDate(@Param("submitDate") Date submitDate);


    @Query("SELECT new com.example.luckySystem.dto.issue.IssueReportDTO(SUM(d.damage_amount), d.issue_name) " +
            "FROM DailyProductionIssuesByEmployee d " +
            "WHERE d.submit_date BETWEEN :fromDate AND :toDate " +
            "GROUP BY d.issue_name")
    List<IssueReportDTO> findIssuesByDateRange(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("SELECT new com.example.luckySystem.dto.issue.IssueReportDTO(SUM(d.damage_amount), d.issue_name) " +
            "FROM DailyProductionIssuesByEmployee d " +
            "WHERE MONTH(d.submit_date) = :month AND YEAR(d.submit_date) = :year " +
            "GROUP BY d.issue_name")
    List<IssueReportDTO> findIssuesByMonth(@Param("month") int month, @Param("year") int year);


    @Query("SELECT d FROM DailyProductionIssuesByEmployee d WHERE d.submit_date >= :startDate")
    List<DailyProductionIssuesByEmployee> findAllFromLastThreeMonths(@Param("startDate") Date startDate);
}
