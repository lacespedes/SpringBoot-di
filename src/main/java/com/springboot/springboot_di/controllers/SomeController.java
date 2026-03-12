package com.springboot.springboot_di.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot_di.models.Product;
import com.springboot.springboot_di.services.ProductService;

@RestController
@RequestMapping("/api")
public class SomeController {
    private ProductService productService = new ProductService();

    @GetMapping("/products")
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        var product = productService.findById(id);
        return product != null ? product : new Product();
    }

    @GetMapping("/products/with-taxes")
    public List<Product> listWithTaxes() {
        return productService.findAllWithTaxes();
    }
}
