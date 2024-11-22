package com.kritica.restapi.service;

import com.kritica.restapi.payload.CategoryDTO;
import com.kritica.restapi.payload.CategoryResponse;

public interface CategoryService {


    public CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    public String createCategory(CategoryDTO category);

    public String deleteCategory(int id);

    public String updateCategory(int id, CategoryDTO category);
}
