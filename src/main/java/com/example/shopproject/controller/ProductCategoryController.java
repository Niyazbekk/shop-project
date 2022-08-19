package com.example.shopproject.controller;

import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasketController.class);
    private final ProductCategoryService productCategoryService;

    @PostMapping("/product-cat")
    public ProductCategoryDto createProductCategory(@Valid @RequestBody ProductCategoryDto productCategory) {
        LOGGER.info("create Product Category");
        return productCategoryService.createProductCategory(productCategory);
    }

    @GetMapping("/product-cats")
    public List<ProductCategory> getAllProductCategories(@RequestParam(defaultValue = "0") int page){
        LOGGER.info("get all categories");
        PageRequest pageRequest = PageRequest.of(page, 3);
        return productCategoryService.getAllProductCategories(pageRequest);
    }

    @GetMapping("/product-cat/{id}")
    public ProductCategoryDto getProductCategoryById(@PathVariable Long id) {
        LOGGER.info("get category by id");
        return productCategoryService.getProductCategoryById(id);
    }

    @PutMapping("/product-cat")
    public ProductCategoryDto updateProductCategory(@Valid @RequestBody ProductCategoryDto product) {
        LOGGER.info("update");
        return productCategoryService.updateProductCategory(product);
    }

    @DeleteMapping("/product-cat/{id}")
    public void deleteProductCategoryById(@PathVariable Long id) {
        LOGGER.info("delete");
        productCategoryService.deleteProductCategoryById(id);
    }

}
