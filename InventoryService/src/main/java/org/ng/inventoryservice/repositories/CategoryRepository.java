package org.ng.inventoryservice.repositories;

import org.ng.inventoryservice.entities.Category;
import org.ng.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
