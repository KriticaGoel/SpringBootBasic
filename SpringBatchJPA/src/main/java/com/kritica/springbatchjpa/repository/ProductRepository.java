package com.kritica.springbatchjpa.repository;

import com.kritica.springbatchjpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
