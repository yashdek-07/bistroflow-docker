package com.bistroflow.repository;

import com.bistroflow.model.OrderDetails;
import com.bistroflow.model.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {
}
