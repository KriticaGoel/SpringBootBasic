package com.kritica.springbatchjpa5.repository;

import com.kritica.springbatchjpa5.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
