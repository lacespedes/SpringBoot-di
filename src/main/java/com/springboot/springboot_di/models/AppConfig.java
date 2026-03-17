package com.springboot.springboot_di.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.Resource;

import com.springboot.springboot_di.repositories.ProductRepository;
import com.springboot.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySources(@PropertySource(value="classpath:config.properties", encoding = "UTF-8"))
public class AppConfig {
    @Value("classpath:json/product.json")
    private Resource resource;


    @Bean("productJson")
    @Primary
    ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson(resource);
    }
}
