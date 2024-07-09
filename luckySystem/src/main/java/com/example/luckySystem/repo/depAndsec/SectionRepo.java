package com.example.luckySystem.repo.depAndsec;


import com.example.luckySystem.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepo extends JpaRepository<Section,String> {

    @Query("SELECT s FROM Section s WHERE s.section_name = :sectionName")
    Section  findSectionIdBySectionName(@Param("sectionName") String sectionName);
}
