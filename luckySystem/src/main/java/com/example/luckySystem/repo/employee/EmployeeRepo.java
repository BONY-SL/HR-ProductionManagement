package com.example.luckySystem.repo.employee;
import com.example.luckySystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,String> {

    @Query(value="SELECT dep_id, COUNT(employee_id) AS employee_count FROM employee WHERE company_status = 'Active' GROUP BY dep_id ")
    List<Object[]> countActiveEmployeesByDepartment();

    @Query(value = "SELECT COUNT(*) AS total_employees FROM employee", nativeQuery = true)
    int getTotalEmployeesCount();

    @Query(value="select * from employee WHERE employee_id=?1",nativeQuery = true)
    Employee getEmployeeByEmployeeID(String employeeId);

    @Query("SELECT e FROM employee e WHERE MONTH(e.dob) = MONTH(CURRENT_DATE) AND DAY(e.dob) = DAY(CURRENT_DATE)")
    List<Employee> findEmployeesWithBirthdaysToday();


    @Query(value = "SELECT * FROM employee e " +
            "WHERE (MONTH(e.dob) = MONTH(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) " +
            "AND DAY(e.dob) = DAY(DATE_ADD(CURDATE(), INTERVAL 1 DAY))) " +
            "OR (MONTH(e.dob) = MONTH(DATE_ADD(CURDATE(), INTERVAL 2 DAY)) " +
            "AND DAY(e.dob) = DAY(DATE_ADD(CURDATE(), INTERVAL 2 DAY))) " +
            "ORDER BY MONTH(e.dob), DAY(e.dob)",
            nativeQuery = true)
    List<Employee> findUpcomingBirthdays();






}
