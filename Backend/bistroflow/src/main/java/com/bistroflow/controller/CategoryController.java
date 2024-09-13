package com.bistroflow.controller;


import com.bistroflow.model.Category;
import com.bistroflow.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //http://localhost:8080/api/v1/category
    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    //http://localhost:8080/api/v1/category/getcatbyid/1
    @GetMapping("/getcatbyid/{id}")
    public ResponseEntity<Category> getCategoryByID(@PathVariable("id") int id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    //http://localhost:8080/api/v1/category/delete/1
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
    }

    //http://localhost:8080/api/v1/category
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        if (category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    //http://localhost:8080/api/v1/category/updatecat/1
    @PutMapping("/updatecat/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(id,category);
        if (updatedCategory != null){
            return ResponseEntity.ok(updatedCategory);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
