package com.example.luckySystem.repo.bottles;
import com.example.luckySystem.entity.DailyFinished;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyFinishedRepostory extends JpaRepository<DailyFinished, Long> {
}

