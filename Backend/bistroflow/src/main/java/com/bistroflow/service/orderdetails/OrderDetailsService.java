package com.bistroflow.service.orderdetails;

import com.bistroflow.model.Orders;
import com.bistroflow.model.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsService {
    Orders saveOrderWithDetails(Orders order, List<OrderDetails> orderDetailsList);
    Optional<Orders> getOrderById(int id);
}
