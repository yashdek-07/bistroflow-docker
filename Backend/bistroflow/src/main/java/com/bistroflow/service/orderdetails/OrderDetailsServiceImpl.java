package com.bistroflow.service.orderdetails;

import com.bistroflow.model.Orders;
import com.bistroflow.model.OrderDetails;
import com.bistroflow.repository.CategoryRepo;
import com.bistroflow.repository.OrdersRepository;
import com.bistroflow.repository.OrderDetailsRepository;
import com.bistroflow.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private OrdersRepository ordersRepository;
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsServiceImpl(OrdersRepository theOrdersRepository, OrderDetailsRepository theOrderDetailsRepository) {
        ordersRepository = theOrdersRepository;
        orderDetailsRepository = theOrderDetailsRepository;
    }

    @Override
    public Orders saveOrderWithDetails(Orders order, List<OrderDetails> orderDetailsList) {
        Orders savedOrder = ordersRepository.save(order);
        for (OrderDetails detail : orderDetailsList) {
            detail.setOrderId(savedOrder.getId());
            orderDetailsRepository.save(detail);
        }
        return savedOrder;
    }

    @Override
    public Optional<Orders> getOrderById(int id) {
        return ordersRepository.findById(id);
    }
}
