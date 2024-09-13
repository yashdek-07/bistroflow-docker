package com.bistroflow.controller;


import com.bistroflow.model.Product;
import com.bistroflow.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    ProductService productService;

    //http://localhost:8080/api/v1/product
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    //http://localhost:8080/api/v1/product/getprodbyid/1
    @GetMapping("/getprodbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product prod = productService.getProductById(id);
        if (prod!= null){
            return ResponseEntity.ok(prod);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //http://localhost:8080/api/v1/product
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/updateproduct/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    //http://localhost:8080/api/v1/product/delete/1
    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable("id") int id){
        productService.deleteProductById(id);
    }
}
