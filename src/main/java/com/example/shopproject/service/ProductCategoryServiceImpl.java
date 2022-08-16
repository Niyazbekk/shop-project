package com.example.shopproject.service;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.repository.ProductCategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService{
    private final ProductCategoryRepository productCategoryRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategoryDto createProductCategory(ProductCategory productCategory) {
        ProductCategory productCategoryEntity = productCategoryRepository.save(productCategory);
        return modelMapper.map(productCategoryEntity, ProductCategoryDto.class);
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategoryDto getProductCategoryById(Long id) {
        return modelMapper.map(productCategoryRepository.getById(id), ProductCategoryDto.class);
    }

    @Override
    public ProductCategoryDto updateProductCategory(ProductCategory productCategory) {
        ProductCategory productCategoryEntity = productCategoryRepository.save(productCategory);
        return modelMapper.map(productCategoryEntity, ProductCategoryDto.class);
    }

    @Override
    public void deleteProductCategoryById(Long id) {
        productCategoryRepository.deleteById(id);
    }


}