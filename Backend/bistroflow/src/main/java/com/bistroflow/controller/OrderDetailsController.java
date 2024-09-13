package com.bistroflow.controller;

import com.bistroflow.exception.OrderNotFoundException;
import com.bistroflow.model.OrderRequest;
import com.bistroflow.model.Orders;
import com.bistroflow.model.OrderDetails;

import com.bistroflow.service.orderdetails.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService theOrderDetailsService) {
        orderDetailsService = theOrderDetailsService;
    }

    //http://localhost:8080/api/v1/orders/save
    @PostMapping("/save")
    public ResponseEntity<Orders> saveOrder(@RequestBody OrderRequest request) {
        Orders order = request.getOrder();
        List<OrderDetails> orderDetailsList = request.getOrderDetails();
        Orders savedOrder = orderDetailsService.saveOrderWithDetails(order, orderDetailsList);
        return ResponseEntity.ok(savedOrder);
    }

    //http://localhost:8080/api/v1/orders/55
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable int id) {
        Orders order = orderDetailsService.getOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + id));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
