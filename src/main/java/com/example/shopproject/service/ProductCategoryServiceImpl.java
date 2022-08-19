package com.example.shopproject.service;

import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService{
    private final ProductCategoryRepository productCategoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategory) {
        productCategoryRepository.save(modelMapper.map(productCategory, ProductCategory.class));
        return productCategory;
    }

    @Override
    public List<ProductCategory> getAllProductCategories(Pageable pageable) {
        return productCategoryRepository.findAll(pageable).getContent();
    }

    @Override
    public ProductCategoryDto getProductCategoryById(Long id) {
        return modelMapper.map(productCategoryRepository.getReferenceById(id), ProductCategoryDto.class);
    }

    @Override
    public ProductCategoryDto updateProductCategory(ProductCategoryDto productCategory) {
        productCategoryRepository.save(modelMapper.map(productCategory, ProductCategory.class));
        return productCategory;
    }

    @Override
    public void deleteProductCategoryById(Long id) {
        productCategoryRepository.deleteById(id);
    }


}