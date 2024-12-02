package com.maven.Services;


import com.maven.Model.Category;
import com.maven.Model.dtos.DtoCategory;
import com.maven.exception.ResourceNotFoundException;

import java.util.List;

public interface ICategoryService {

    Category addCategory(DtoCategory category);

    List<Category> getAllCategories();

    void deleteCategory(Long categoryId);

    Category updateCategory(DtoCategory category) throws ResourceNotFoundException;
}
