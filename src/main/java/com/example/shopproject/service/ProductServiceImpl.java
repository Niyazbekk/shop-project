package com.example.shopproject.service;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ProductDto createProduct(Product product){
        Product productEntity = productRepository.save(product);
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    };

    @Override
    public ProductDto getProductById(Long id){
        return modelMapper.map(productRepository.getById(id), ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Product product){
        Product productEntity = productRepository.save(product);
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @Override
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
