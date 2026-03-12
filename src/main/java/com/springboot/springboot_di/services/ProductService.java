package com.springboot.springboot_di.services;

import java.util.List;

import com.springboot.springboot_di.models.Product;
import com.springboot.springboot_di.repositories.ProductRepository;

public class ProductService {
    private ProductRepository productRepository = new ProductRepository();
    
    public List<Product> findAll() {
        return productRepository.findAll();
    }   

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllWithTaxes() {
        return productRepository.findAll().stream()
            .map(product -> new Product(
                product.getId(),
                product.getName(),
                product.getPrice() * 1.2 // Adding 20% tax
            ))
            .toList();
    }
}
