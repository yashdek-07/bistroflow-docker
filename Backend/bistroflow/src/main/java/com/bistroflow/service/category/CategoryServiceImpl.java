package com.bistroflow.service.category;

import com.bistroflow.model.Category;
import com.bistroflow.exception.ApiRequestException;
import com.bistroflow.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategory() {
        //return categoryRepo.findAll();
        return categoryRepo.findAll().stream()
                .sorted(Comparator.comparing(Category::getCategoryName))
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryById(int id) {
        //return categoryRepo.findById(id).orElse(null);
        //return categoryRepo.getReferenceById(id);

        return categoryRepo.findById(id)
                .orElseThrow(
                        () -> new ApiRequestException("Category not found with this ID: " + id)
                );
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        /*Category existingCat = categoryRepo.findById(id).orElse(null);

        if (existingCat != null){
            existingCat.setCategoryName(category.getCategoryName());
            return categoryRepo.save(existingCat);
        }
        return  null;*/

        return categoryRepo.findById(id)
                .map(
                        existingCategory -> {
                            existingCategory.setCategoryName(category.getCategoryName());
                            return categoryRepo.save(existingCategory);
                        })
                .orElseThrow(
                        () -> new ApiRequestException("Cannot update the category ")
                );
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }
}
