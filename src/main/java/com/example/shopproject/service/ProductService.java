package com.example.shopproject.service;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto createProduct(Product product);
    List<Product> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Product product);
    void deleteProductById(Long id);
}
