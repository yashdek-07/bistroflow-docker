package com.bistroflow.service.product;

import com.bistroflow.model.Product;
import com.bistroflow.exception.ApiRequestException;
import com.bistroflow.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> getAllProduct() {
        //return productRepo.findAll();
        return productRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getProductName))
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(int id) {
        //return productRepo.getReferenceById(id);
        return productRepo.findById(id)
                .orElseThrow(
                ()-> new ApiRequestException("Product not found with this ID: "+id)
        );
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return productRepo.findById(id).map(
                existingProduct -> {
                    existingProduct.setProductId(product.getProductId());
                    existingProduct.setProductName(product.getProductName());
                    existingProduct.setProductDescription(product.getProductDescription());
                    existingProduct.setProductPrice(product.getProductPrice());
                    existingProduct.setCategory(product.getCategory());
                    return productRepo.save(existingProduct);
                })
                .orElseThrow(
                ()->new ApiRequestException("Product not found with this ID: "+id)
        );
    }

    @Override
    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }
}
