package com.example.shopproject.repository;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsBy();
    Product getProductById(Long id);
    void deleteProductById(Long id);
}
