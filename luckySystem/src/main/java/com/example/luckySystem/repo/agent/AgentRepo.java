package com.example.luckySystem.repo.agent;
import com.example.luckySystem.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AgentRepo extends JpaRepository<Agent,Long> {

    List<Agent> findAllByDeletedAtIsNull();

}
