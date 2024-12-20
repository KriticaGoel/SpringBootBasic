package com.kritica.springbatchjpa.service;

import com.kritica.springbatchjpa.model.Product;
import com.kritica.springbatchjpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProduct(){
        return productRepository.findAll();
    }
}
