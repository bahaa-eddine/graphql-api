package org.ng.inventoryservice.controllers;

import org.ng.inventoryservice.dtos.ProductRequestDTO;
import org.ng.inventoryservice.entities.Product;
import org.ng.inventoryservice.mappers.ProductMapper;
import org.ng.inventoryservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @QueryMapping
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @QueryMapping Product findProductById(@Argument String id) {
        return productRepository.findById(id).orElseThrow(() ->
            new RuntimeException(String.format("Product %s not found!", id))
        );
    }

    @MutationMapping
    public Product saveProduct(@Argument ProductRequestDTO productRequestDTO){
        return productRepository.save(productMapper.toEntity(productRequestDTO));
    }

    @MutationMapping
    public Product updateProduct(@Argument String id, @Argument ProductRequestDTO productRequestDTO){
        String productId = productRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Product %s not exist", id))).getId();
        productRequestDTO.setId(productId);
        return productRepository.save(productMapper.toEntity(productRequestDTO));
    }

    @MutationMapping
    public String deleteProduct(@Argument String id){
        productRepository.deleteById(id);
        return String.format("Product %s deleted successfully", id);
    }
}
