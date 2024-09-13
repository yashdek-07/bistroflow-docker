package com.bistroflow.repository;

import com.bistroflow.model.MonthWiseCategorySales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthWiseCategorySalesRepository extends JpaRepository<MonthWiseCategorySales, String> {
    @Query(value = "SELECT * FROM GetMonthWiseCategorySales();", nativeQuery = true)
     List<MonthWiseCategorySales> findMonthWiseCategorySales();

}
