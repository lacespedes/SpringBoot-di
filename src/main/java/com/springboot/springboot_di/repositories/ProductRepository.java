package com.springboot.springboot_di.repositories;

import java.util.List;

import com.springboot.springboot_di.models.Product;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long id);

}