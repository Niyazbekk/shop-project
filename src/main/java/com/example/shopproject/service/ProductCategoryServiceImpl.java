package com.example.shopproject.service;

import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

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
    public Page<ProductCategoryDto> getAllProductCategories(Pageable pageable) {
        return new PageImpl<>(productCategoryRepository.findAll(pageable).stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryDto.class))
                .collect(Collectors.toList()));
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