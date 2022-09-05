package com.example.shopproject.service;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto product){
        productRepository.save(modelMapper.map(product, Product.class));
        return product;
    }

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable){
        return new PageImpl<>(productRepository.findAll(pageable).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList()));
    }

    @Override
    public ProductDto getProductById(Long id){
        return modelMapper.map(productRepository.getReferenceById(id), ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto product){
        productRepository.save(modelMapper.map(product, Product.class));
        return product;
    }

    @Override
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
