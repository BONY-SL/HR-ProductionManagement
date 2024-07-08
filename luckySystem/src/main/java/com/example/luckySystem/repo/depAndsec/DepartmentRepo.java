package com.example.luckySystem.repo.depAndsec;
import com.example.luckySystem.dto.employee.DepartmentEmployeeCountDto;
import com.example.luckySystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, String> {

    @Query("SELECT new com.example.luckySystem.dto.employee.DepartmentEmployeeCountDto(d.department_name, COUNT(e)) " +
            "FROM Department d LEFT JOIN d.employees e " +
            "GROUP BY d.department_name")
    List<DepartmentEmployeeCountDto> getDepartmentEmployeeCounts();

    @Query("SELECT d FROM Department d WHERE d.department_name = :departmentName")
    Department  findDepartmentIdByDepartmentName(@Param("departmentName") String departmentName);
}

