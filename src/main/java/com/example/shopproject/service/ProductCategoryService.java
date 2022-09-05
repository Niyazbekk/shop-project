package com.example.shopproject.service;

import com.example.shopproject.entity.dto.ProductCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductCategoryService {
    ProductCategoryDto createProductCategory(ProductCategoryDto productCategory);
    Page<ProductCategoryDto> getAllProductCategories(Pageable pageable);
    ProductCategoryDto getProductCategoryById(Long id);
    ProductCategoryDto updateProductCategory(ProductCategoryDto productCategory);
    void deleteProductCategoryById(Long id);
}