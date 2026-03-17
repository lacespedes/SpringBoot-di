package com.springboot.springboot_di.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.springboot.springboot_di.models.Product;
import com.springboot.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private Environment env; 
    
    //Otra forma de inyectar el valor de la propiedad config.tax.rate utilizando @Value, 
    // pero se comenta para usar Environment en su lugar
    /* @Value("${config.tax.rate}")
    private String taxRate; */


    //@Autowired --- IGNORE ---
    //@Qualifier("productList") --- IGNORE ---
    private ProductRepository productRepository;

    // Constructor-based dependency injection
    // Sprint injectará automáticamente el ProductRepositoryImpl aquí, por lo tanto no se necesita la anotación @Autowired
    // Se utiliza @Qualifier para especificar cuál implementación de ProductRepository se debe inyectar, en este caso "productList"
    //public ProductServiceImpl(@Qualifier("productList") ProductRepository productRepository) {
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
                //product.getPrice() * (1.0 + Double.parseDouble(taxRate)) // se comenta para usar Environment en su lugar
                product.getPrice() * (1.0 + env.getProperty("config.tax.rate", Double.class)) // Aquí se obtiene el valor de la propiedad config.tax.rate utilizando Environment
            ))
            .toList();
    }
}
