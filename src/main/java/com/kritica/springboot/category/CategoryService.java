package com.kritica.springboot.category;

import com.kritica.springboot.category.payload.CategoryDTO;
import com.kritica.springboot.category.payload.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {


    public CategoryResponse getCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);

    public String createCategory(CategoryDTO category);

    public String deleteCategory(int id);

    public String updateCategory(int id, CategoryDTO category);
}
