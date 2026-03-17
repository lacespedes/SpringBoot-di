package com.springboot.springboot_di.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.springboot.springboot_di.repositories.ProductRepository;
import com.springboot.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySources(@PropertySource(value="classpath:config.properties", encoding = "UTF-8"))
public class AppConfig {
    @Bean
    @Primary
    ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson();
    }
}
