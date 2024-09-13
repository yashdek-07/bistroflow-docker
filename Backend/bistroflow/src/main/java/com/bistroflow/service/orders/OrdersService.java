package com.bistroflow.service.orders;

import com.bistroflow.model.Category;
import com.bistroflow.model.Orders;
import com.bistroflow.model.Product;

import java.util.List;
import java.util.Optional;


public interface OrdersService {
    List<Category> getAllCategories();
    List<Product> getProductsByCategory(Integer categoryId);
}
