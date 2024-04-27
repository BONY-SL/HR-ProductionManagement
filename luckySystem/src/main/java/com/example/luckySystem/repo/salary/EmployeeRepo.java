package com.example.luckySystem.repo.salary;



import com.example.luckySystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

    @Query(value="SELECT dep_id, COUNT(employee_id) AS employee_count FROM employee WHERE company_status = 'Active' GROUP BY dep_id ")
    List<Object[]> countActiveEmployeesByDepartment();

    @Query(value = "SELECT COUNT(*) AS total_employees FROM employee", nativeQuery = true)
    int getTotalEmployeesCount();

    @Query(value="select * from employee WHERE employee_id=?1",nativeQuery = true)
    Employee getEmployeeByEmployeeID(String employeeId);

}
