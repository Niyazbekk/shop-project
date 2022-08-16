package com.example.shopproject.service;

import com.example.shopproject.entity.Product;
import com.example.shopproject.repository.BasketRepository;
import com.example.shopproject.repository.ProductRepository;
import com.example.shopproject.repository.Product_categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    };

    public List<Product> getAllProducts(){
        return productRepository.getProductsBy();
    };

    public Product getProductById(Long id){
        return productRepository.getProductById(id);
    };

    public Product updateProdictById(Product product){
        Product product1 = productRepository.getProductById(product.getId());
        product.setId(product1.getId());
        product = productRepository.save(product);

        return product;
    };

    public void deleteProductById(Long id){
        productRepository.deleteProductById(id);
    };
}
