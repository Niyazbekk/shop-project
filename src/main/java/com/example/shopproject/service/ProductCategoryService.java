package com.example.shopproject.service;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {
    ProductCategoryDto createProductCategory(ProductCategory productCategory);
    List<ProductCategory> getAllProductCategories();
    ProductCategoryDto getProductCategoryById(Long id);
    ProductCategoryDto updateProductCategory(ProductCategory productCategory);
    void deleteProductCategoryById(Long id);
}
