package com.bistroflow.controller;

import com.bistroflow.model.Category;
import com.bistroflow.model.Product;
import com.bistroflow.service.orders.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {
    private OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService theOrdersService) {
        ordersService = theOrdersService;
    }

    //http://localhost:8080/api/v1/orders/categories
    @GetMapping("/orders/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = ordersService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/orders/products/1
    @GetMapping("/orders/products/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Integer categoryId) {
        List<Product> products = ordersService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
