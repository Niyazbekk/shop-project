package com.example.shopproject.controller;

import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.service.ProductService;
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
public class ProductController {
    private final ProductService productService;
    @PostMapping("/product")
    public ProductDto createProduct(@Valid @RequestBody ProductDto product) {
        log.info("Rest request to create product");
        return productService.createProduct(product);
    }
    @GetMapping("/products")
    public Page<ProductDto> getAllProducts(@PageableDefault(value = 5, page = 0) Pageable pageable){
        log.info("Rest request to get all products by pages with page size = {} and page number = {}" , pageable.getPageSize() , pageable.getPageNumber());
        return productService.getAllProducts(pageable);
    }
    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        log.info("Rest request to get product by id = {}",id);
        return productService.getProductById(id);
    }
    @PutMapping("/product")
    public ProductDto updateProduct(@Valid @RequestBody ProductDto product) {
        log.info("Rest request to update product with id = {}", product.getId());
        return productService.updateProduct(product);
    }
    @DeleteMapping("/product/{id}")
    public void deleteProductById(@PathVariable Long id) {
        log.info("Rest request to delete product by id = {}",id);
        productService.deleteProductById(id);
    }
}