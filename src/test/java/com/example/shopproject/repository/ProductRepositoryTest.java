package com.example.shopproject.repository;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.ProductCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
    //given
    @MockBean
    private ProductRepository productRepository;
    private final Product product = Product.builder().id(1L).name("product").description("desc").cost(2000.0).productCategory(new ProductCategory()).build();
    private final List<Product> products = Collections.singletonList(product);

    @Before
    public void init() {
        productRepository.save(product);
    }

    @Test
    public void getProductByIdTest() {
        //given

        //when
        var mockito = when(productRepository.getReferenceById(anyLong()));

        //then
        mockito.thenReturn(product);

        Product result = productRepository.getReferenceById(1L);
        assertEquals(result, product);
    }

    @Test
    public void getAllProductsTest() {
        //given

        //when
        var mockito = when(productRepository.findAll());

        //then
        mockito.thenReturn(products);

        List<Product> result = productRepository.findAll();
        assertEquals(result.get(0), product);
    }

    @Test
    public void deleteProductTest() {
        //given

        //when

        //then

        productRepository.delete(product);
        verify(productRepository).delete(product);
    }
}
