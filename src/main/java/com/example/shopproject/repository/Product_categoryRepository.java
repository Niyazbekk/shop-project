package com.example.shopproject.repository;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.Product_category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Product_categoryRepository extends JpaRepository<Product_category, Long> {
    List<Product_category> getProduct_categoriesBy();
    Product_category getProduct_categoryById(Long id);
    void deleteProduct_categoryById(Long id);
}
