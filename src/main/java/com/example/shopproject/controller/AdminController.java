package com.example.shopproject.controller;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.Product_category;
import com.example.shopproject.service.ProductService;
import com.example.shopproject.service.Product_categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    Product_categoryService product_categoryService;

    @PostMapping("/product")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/product/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productID}")
    public Product getProductById(@PathVariable Long productID) {
        return productService.getProductById(productID);
    }

    @PutMapping("/product")
    public Product updateProductById(@Valid @RequestBody Product product) {
        return productService.updateProdictById(product);
    }

    @DeleteMapping("/product/{productID}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productID) {
        productService.deleteProductById(productID);
        return new ResponseEntity<>("Успешно удален", HttpStatus.OK);
    }

    @PostMapping("/product_cat")
    public Product_category createProductCategory(@Valid @RequestBody Product_category product_category) {
        return product_categoryService.createProduct_category(product_category);
    }

    @GetMapping("/product_cat/all")
    public List<Product_category> getAllProductCategories(){
        return product_categoryService.getAllProduct_categories();
    }

    @GetMapping("/product_cat/{product_catID}")
    public Product_category getProductCategoryById(@PathVariable Long product_catID) {
        return product_categoryService.getProduct_categoryById(product_catID);
    }

    @PutMapping("/product_cat")
    public Product_category updateProductCategoryById(@Valid @RequestBody Product_category product) {
        return product_categoryService.updateProduct_categoryById(product);
    }

    @DeleteMapping("/product_cat/{product_catID}")
    public ResponseEntity<String> deleteProductCategoryById(@PathVariable Long product_catID) {
        product_categoryService.deleteProduct_categoryById(product_catID);
        return new ResponseEntity<>("Успешно удален", HttpStatus.OK);
    }

}
