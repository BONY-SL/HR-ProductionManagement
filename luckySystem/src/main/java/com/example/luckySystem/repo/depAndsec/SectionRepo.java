package com.example.luckySystem.repo.depAndsec;


import com.example.luckySystem.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepo extends JpaRepository<Section,String> {
}
