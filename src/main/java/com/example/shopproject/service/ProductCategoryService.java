package com.example.shopproject.service;

import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {
    ProductCategoryDto createProductCategory(ProductCategoryDto productCategory);
    List<ProductCategory> getAllProductCategories(Pageable pageable);
    ProductCategoryDto getProductCategoryById(Long id);
    ProductCategoryDto updateProductCategory(ProductCategoryDto productCategory);
    void deleteProductCategoryById(Long id);
}
