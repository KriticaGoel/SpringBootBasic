package com.kritica.springboot.category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {


    public List<Category> getCategories();

    public String createCategory(Category category);

    public String deleteCategory(int id);

    public String updateCategory(int id, Category category);
}
