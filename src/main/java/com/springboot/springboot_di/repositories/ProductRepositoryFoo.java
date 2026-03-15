package com.springboot.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.springboot.springboot_di.models.Product;

@Primary //Se comenta esta anotación para que Spring no sepa cuál implementar usar y así se pueda probar la inyección de dependencias con el nuevo repositorio ProductRepositoryFoo
@Repository("productFoo") // Especificamos un nombre para el bean, esto es útil para la inyección de dependencias cuando hay múltiples implementaciones de la misma interfaz
public class ProductRepositoryFoo implements ProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Sample Product", 100.0));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Sample Product", 100.0);
    }

}
