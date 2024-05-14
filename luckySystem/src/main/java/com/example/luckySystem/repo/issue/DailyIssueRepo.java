package com.example.luckySystem.repo.issue;

import com.example.luckySystem.dto.issue.GetMonthlyIssueDTO;
import com.example.luckySystem.entity.DailyProductionIssuesByEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DailyIssueRepo extends JpaRepository<DailyProductionIssuesByEmployee,Long> {

    @Query("SELECT new com.example.luckySystem.dto.issue.GetMonthlyIssueDTO(d.issue_name, COUNT(d.issue_name), SUM(d.damage_amount)) " +
            "FROM DailyProductionIssuesByEmployee d " +
            "WHERE MONTH(d.submit_date) = :month AND YEAR(d.submit_date) = :year " +
            "GROUP BY d.issue_name")
    List<GetMonthlyIssueDTO> findIssuesGroupedByIssueName(@Param("month") int month, @Param("year") int year);

}
