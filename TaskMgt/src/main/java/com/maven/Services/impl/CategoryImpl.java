package com.maven.Services.impl;

import com.maven.Model.Category;
import com.maven.Model.dtos.DtoCategory;
import com.maven.Repository.ICategoryRepository;
import com.maven.Services.ICategoryService;
import com.maven.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Category addCategory(DtoCategory category) {

        Category c=new Category();
        c.setName(category.getName());
        c.setNotes(category.getNotes());
        c.setDate(LocalDate.now().toString());
        return categoryRepository.save(c);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category updateCategory(DtoCategory category) throws ResourceNotFoundException {

        Category existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existingCategory.setName(category.getName());
        existingCategory.setNotes(category.getNotes());
        existingCategory.setDate(category.getDate());
        return categoryRepository.save(existingCategory);
    }
}

