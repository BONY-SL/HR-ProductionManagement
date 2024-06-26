package com.example.luckySystem.repo.salary;

import com.example.luckySystem.entity.Allowances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AllowanceRepo extends JpaRepository<Allowances,Long> {

    @Query(value="select * from allowances where allowances_id=?1",nativeQuery = true)
    Allowances getAllowancesById(String allowanceID);
}
