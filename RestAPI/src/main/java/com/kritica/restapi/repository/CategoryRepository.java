package com.kritica.restapi.repository;

import com.kritica.restapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByCategoryName(String categoryName);
}
