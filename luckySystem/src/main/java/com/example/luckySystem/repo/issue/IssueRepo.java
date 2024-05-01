package com.example.luckySystem.repo.issue;

import com.example.luckySystem.entity.ProductionIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepo extends JpaRepository<ProductionIssue,Long> {
}
