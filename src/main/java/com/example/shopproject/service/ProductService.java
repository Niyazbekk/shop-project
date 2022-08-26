package com.example.shopproject.service;

import com.example.shopproject.entity.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {
    ProductDto createProduct(ProductDto product);
    Page<ProductDto> getAllProducts(Pageable pageable);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(ProductDto product);
    void deleteProductById(Long id);
}