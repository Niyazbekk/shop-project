package com.example.shopproject.service;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto createProduct(ProductDto product);
    List<Product> getAllProducts(Pageable pageable);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(ProductDto product);
    void deleteProductById(Long id);
}
