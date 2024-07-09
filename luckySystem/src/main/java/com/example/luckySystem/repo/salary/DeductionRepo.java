package com.example.luckySystem.repo.salary;
import com.example.luckySystem.entity.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeductionRepo extends JpaRepository<Deduction,Long> {

    @Query(value="select * from deduction where deduction_id=?1",nativeQuery = true)
    Deduction getDeductionByDeductionID(String deductionId);

    @Query(value = "SELECT * FROM deduction WHERE job_role = :jobRole AND department_name = :departmentName AND section_name=:sectionName", nativeQuery = true)
    List<Deduction> findByCustomQuery(@Param("jobRole") String jobRole, @Param("departmentName") String departmentName, @Param("sectionName") String sectionName);

}
