package com.example.luckySystem.repo.issue;

import com.example.luckySystem.entity.DailyProductionIssuesByEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DailyIssueRepo extends JpaRepository<DailyProductionIssuesByEmployee,Long> {
}
