package com.cineverse.erpc.product.repository;

import com.cineverse.erpc.product.aggregate.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
