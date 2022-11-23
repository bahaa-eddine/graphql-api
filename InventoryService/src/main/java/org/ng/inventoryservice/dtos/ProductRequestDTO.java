package org.ng.inventoryservice.dtos;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String id;
    private String name;
    private Double price;
    private Integer quantity;
    private Long categoryId;
}
