package com.example.shopproject.controller;

import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    private ProductCategoryController(ProductCategoryService productCategoryService){
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/product-cat")
    public ProductCategoryDto createProductCategory(@Valid @RequestBody ProductCategory productCategory) {
        return productCategoryService.createProductCategory(productCategory);
    }

    @GetMapping("/product-cats")
    public List<ProductCategory> getAllProductCategories(){
        return productCategoryService.getAllProductCategories();
    }

    @GetMapping("/product-cat/{id}")
    public ProductCategoryDto getProductCategoryById(@PathVariable Long id) {
        return productCategoryService.getProductCategoryById(id);
    }

    @PutMapping("/product-cat")
    public ProductCategoryDto updateProductCategory(@Valid @RequestBody ProductCategory product) {
        return productCategoryService.updateProductCategory(product);
    }

    @DeleteMapping("/product-cat/{id}")
    public String deleteProductCategoryById(@PathVariable Long id) {
        productCategoryService.deleteProductCategoryById(id);
        return "Успешно удален";
    }

}
