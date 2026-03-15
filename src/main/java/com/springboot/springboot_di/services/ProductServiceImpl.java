package com.springboot.springboot_di.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.springboot_di.models.Product;
import com.springboot.springboot_di.repositories.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }   

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
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
