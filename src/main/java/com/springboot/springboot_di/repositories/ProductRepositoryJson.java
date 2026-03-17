package com.springboot.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springboot_di.models.Product;

public class ProductRepositoryJson implements ProductRepository {

    private List<Product> products;

    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");
        // Codigo para cargar el archivo JSON y convertirlo a una lista de productos
        loadProductsFromJson(resource);
    }

    public ProductRepositoryJson(Resource resource) {
        loadProductsFromJson(resource);
    }

    private void loadProductsFromJson(Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //Otra alternativa para cargar el archivo JSON, usando getInputStream() en lugar de getFile(), esto es útil cuando el archivo JSON 
            // está empaquetado dentro de un JAR, ya que getFile() no funcionará en ese caso. Con getInputStream(), se puede leer el contenido 
            // del archivo JSON sin importar dónde esté ubicado, ya sea en el sistema de archivos o dentro de un JAR.
            //products = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
            products = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
