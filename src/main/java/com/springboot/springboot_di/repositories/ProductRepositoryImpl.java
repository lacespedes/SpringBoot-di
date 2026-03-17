package com.springboot.springboot_di.repositories;

import java.util.List;

//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
//import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.springboot.springboot_di.models.Product;

//@Primary -- Se comenta esta anotación para que Spring no sepa cuál implementar usar y así se pueda probar 
// la inyección de dependencias con el nuevo repositorio ProductRepositoryFoo

// la diferencia entre @RequestScope y @SessionScope es que @RequestScope crea una nueva instancia del bean 
// para cada solicitud HTTP, mientras que @SessionScope crea una nueva instancia del bean para cada sesión 
// de usuario. En este caso, se ha cambiado a @SessionScope para que se mantenga la misma instancia del 
// ProductRepositoryImpl durante toda la sesión del usuario, lo que puede ser útil para probar la inyección de 
// dependencias con el nuevo repositorio ProductRepositoryFoo.
// por otro lado Singleton es el scope por defecto en Spring, lo que significa que se crea una única instancia 
// del bean para toda la aplicación, y esta instancia se comparte entre todas las solicitudes y sesiones. 
// En este caso, al cambiar a @SessionScope, se asegura que cada sesión de usuario tenga su propia instancia 
// del ProductRepositoryImpl, lo que puede ser útil para probar la inyección de dependencias con el nuevo 
// repositorio ProductRepositoryFoo sin afectar a otras sesiones de usuario.
//@RequestScope // Cambiamos el scope del bean a request para que se cree una nueva instancia de ProductRepositoryImpl por cada solicitud HTTP, esto es útil para probar la inyección de dependencias con el nuevo repositorio ProductRepositoryFoo
@SessionScope // Cambiamos el scope del bean a session para que se cree una nueva instancia de ProductRepositoryImpl por cada sesión de usuario, esto es útil para probar la inyección de dependencias con el nuevo repositorio ProductRepositoryFoo sin afectar a otras sesiones de usuario
@Repository("productList") // Especificamos un nombre para el bean, esto es útil para la inyección de dependencias cuando hay múltiples implementaciones de la misma interfaz
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
