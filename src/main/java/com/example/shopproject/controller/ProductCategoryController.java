package com.example.shopproject.controller;

import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping("/product-cat")
    public ProductCategoryDto createProductCategory(@Valid @RequestBody ProductCategoryDto productCategory) {
        log.info("Rest request to create product category");
        return productCategoryService.createProductCategory(productCategory);
    }

    @GetMapping("/product-cats")
    public Page<ProductCategoryDto> getAllProductCategories(@PageableDefault(value = 5, page = 0) Pageable pageable){
        log.info("Rest request to get all product categories by pages with page size = {} and page number = {}",pageable.getPageSize() , pageable.getPageNumber());
        return productCategoryService.getAllProductCategories(pageable);
    }

    @GetMapping("/product-cat/{id}")
    public ProductCategoryDto getProductCategoryById(@PathVariable Long id) {
        log.info("Rest request to get product category by id = {}" , id);
        return productCategoryService.getProductCategoryById(id);
    }

    @PutMapping("/product-cat")
    public ProductCategoryDto updateProductCategory(@Valid @RequestBody ProductCategoryDto product) {
        log.info("Rest request to update product category with id = {}", product.getId());
        return productCategoryService.updateProductCategory(product);
    }

    @DeleteMapping("/product-cat/{id}")
    public void deleteProductCategoryById(@PathVariable Long id) {
        log.info("Rest request to delete product category by id = {}",id);
        productCategoryService.deleteProductCategoryById(id);
    }

}
