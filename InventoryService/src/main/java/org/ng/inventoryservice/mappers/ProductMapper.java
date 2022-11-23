package org.ng.inventoryservice.mappers;

import org.ng.inventoryservice.dtos.ProductRequestDTO;
import org.ng.inventoryservice.entities.Product;
import org.ng.inventoryservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public Product toEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        if(productRequestDTO.getId() == null)
            product.setId(UUID.randomUUID().toString());
        else
            product.setId(productRequestDTO.getId());
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setCategory(categoryRepository.findById(productRequestDTO.getCategoryId()).orElse(null));
        return product;
    }

}
