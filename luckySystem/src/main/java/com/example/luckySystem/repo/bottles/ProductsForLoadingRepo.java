package com.example.luckySystem.repo.bottles;


import com.example.luckySystem.entity.GoodProductsForLoading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsForLoadingRepo extends JpaRepository<GoodProductsForLoading,Long> {
}
