package com.bistroflow.repository;

import com.bistroflow.model.UserOrderCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserOrderCountRepository extends JpaRepository<UserOrderCount, Long> {
    @Query(value = "SELECT * FROM GetUserOrderCount()", nativeQuery = true)
    List<UserOrderCount> getUserOrderCount();
}
