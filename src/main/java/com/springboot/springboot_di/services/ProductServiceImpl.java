package com.springboot.springboot_di.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.springboot_di.models.Product;
import com.springboot.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    // @Autowired --- IGNORE ---
    private ProductRepository productRepository;

    // Constructor-based dependency injection
    // Sprint injectará automáticamente el ProductRepositoryImpl aquí, por lo tanto no se necesita la anotación @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
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
