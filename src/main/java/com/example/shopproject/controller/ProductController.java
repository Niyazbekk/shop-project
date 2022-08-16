package com.example.shopproject.controller;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.service.ProductService;
import com.example.shopproject.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @PostMapping("/product")
    public ProductDto createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/product")
    public ProductDto updateProduct(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "Успешно удален";
    }
}
