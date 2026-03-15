package com.springboot.springboot_di.repositories;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.springboot_di.models.Product;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    List<Product> data;

    public ProductRepositoryImpl() {
        this.data = List.of(
            new Product(1L, "Laptop", 999.99),
            new Product(2L, "Smartphone", 499.99),
            new Product(3L, "Tablet", 299.99),
            new Product(4L, "Headphones", 199.99),
            new Product(5L, "Smartwatch", 199.99),
            new Product(6L, "Camera", 599.99),
            new Product(7L, "Printer", 149.99),
            new Product(8L, "Monitor", 249.99),
            new Product(9L, "Keyboard", 49.99)
        );
    }

    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream()
                   .filter(product -> product.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }
}
