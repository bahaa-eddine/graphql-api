package org.ng.inventoryservice;

import org.ng.inventoryservice.entities.Category;
import org.ng.inventoryservice.entities.Product;
import org.ng.inventoryservice.repositories.CategoryRepository;
import org.ng.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ) {
        return args -> {
            Arrays.asList("Computer", "Smartphone").forEach(cat -> {
                Category category = Category.builder().name(cat).build();
                categoryRepository.save(category);
            });
            categoryRepository.findAll().forEach(category -> {
                for (int i = 0; i < 3; i++) {
                    Product product = Product
                            .builder()
                            .id(UUID.randomUUID().toString())
                            .name(category.getName() + " " + i)
                            .price(100+Math.random()*100)
                            .quantity(new Random().nextInt(100))
                            .category(category)
                            .build();
                    productRepository.save(product);
                }
            });
        };
    }

}
