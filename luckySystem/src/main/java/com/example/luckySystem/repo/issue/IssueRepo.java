package com.example.luckySystem.repo.issue;

import com.example.luckySystem.entity.ProductionIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssueRepo extends JpaRepository<ProductionIssue,Long> {
}
