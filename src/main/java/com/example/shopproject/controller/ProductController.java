package com.example.shopproject.controller;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @PostMapping("/product")
    public ProductDto createProduct(@Valid @RequestBody ProductDto product) {
        LOGGER.info("create product");
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public Page<Product> getAllProducts(@PageableDefault(value = 5, page = 0) Pageable pageable){
        LOGGER.info("get all products");
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        LOGGER.info("get product by id");
        return productService.getProductById(id);
    }

    @PutMapping("/product")
    public ProductDto updateProduct(@Valid @RequestBody ProductDto product) {
        LOGGER.info("update");
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProductById(@PathVariable Long id) {
        LOGGER.info("delete");
        productService.deleteProductById(id);
    }
}
