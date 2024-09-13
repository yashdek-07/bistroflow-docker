package com.bistroflow.repository;

import com.bistroflow.model.DateWiseTotalSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DateWiseSalesRepository extends JpaRepository<DateWiseTotalSales, Date> {

    @Query(value = "SELECT * FROM GetDateWiseSales()", nativeQuery = true)
    List<DateWiseTotalSales> findDateWiseSales();
}
