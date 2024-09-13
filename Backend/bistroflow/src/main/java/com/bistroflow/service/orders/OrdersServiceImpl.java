package com.bistroflow.service.orders;

import com.bistroflow.model.Category;
import com.bistroflow.model.Product;
import com.bistroflow.repository.CategoryRepo;
import com.bistroflow.repository.OrdersRepository;
import com.bistroflow.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public OrdersServiceImpl(OrdersRepository theOrdersRepository, ProductRepo theProductRepo, CategoryRepo theCategoryRepo) {
        ordersRepository = theOrdersRepository;
        productRepo = theProductRepo;
        categoryRepo = theCategoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepo.findByCategory_Id(categoryId);
    }
}
