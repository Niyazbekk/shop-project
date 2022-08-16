package com.example.shopproject.service;

import com.example.shopproject.entity.Product_category;
import com.example.shopproject.repository.BasketRepository;
import com.example.shopproject.repository.Product_categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Product_categoryService {
    @Autowired
    BasketRepository basketRepository;

    @Autowired
    Product_categoryRepository product_categoryRepository;

    public Product_category createProduct_category(Product_category product_category) {
        return product_categoryRepository.save(product_category);
    };

    public List<Product_category> getAllProduct_categories() {
        return product_categoryRepository.getProduct_categoriesBy();
    };

    public Product_category getProduct_categoryById(Long id) {
        return product_categoryRepository.getProduct_categoryById(id);
    };

    public Product_category updateProduct_categoryById(Product_category product_category) {
        Product_category product_category1 = product_categoryRepository.getProduct_categoryById(product_category.getId());
        product_category.setId(product_category1.getId());
        product_category = product_categoryRepository.save(product_category);
        return product_category;
    };

    public void deleteProduct_categoryById(Long id) {
        product_categoryRepository.deleteProduct_categoryById(id);
    };


}