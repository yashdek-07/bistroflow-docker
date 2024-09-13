package com.bistroflow.service.category;

import com.bistroflow.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(int id);
    Category createCategory(Category category);
    Category updateCategory(int id, Category category);
    void deleteCategory(int id); // Delete by ID
}
