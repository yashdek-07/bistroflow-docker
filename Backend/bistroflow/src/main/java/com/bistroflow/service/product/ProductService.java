package com.bistroflow.service.product;

import com.bistroflow.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product getProductById(int id);
    Product createProduct(Product product);
    Product updateProduct(int id, Product product);
    void deleteProductById(int id);
}
