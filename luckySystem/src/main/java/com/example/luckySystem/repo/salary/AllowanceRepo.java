package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.Allowances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllowanceRepo extends JpaRepository<Allowances,Long> {

    @Query(value="select * from allowances where allowances_id=?1",nativeQuery = true)
    Allowances getAllowancesById(String allowanceID);

   // @Query(value = "SELECT * FROM allowances WHERE job_role = :jobRole AND department_name = :departmentName AND section_name=:sectionName", nativeQuery = true)
   // Allowances findByCustomQuery(@Param("jobRole") String jobRole, @Param("departmentName") String departmentName, @Param("sectionName") String sectionName);

    @Query(value = "SELECT * FROM allowances WHERE job_role = :jobRole AND department_name = :departmentName AND section_name = :sectionName", nativeQuery = true)
    List<Allowances> findByCustomQuery(@Param("jobRole") String jobRole, @Param("departmentName") String departmentName, @Param("sectionName") String sectionName);



}
