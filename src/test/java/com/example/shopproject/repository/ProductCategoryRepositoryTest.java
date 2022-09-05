package com.example.shopproject.repository;

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
public class ProductCategoryRepositoryTest {
    //given
    @MockBean
    private ProductCategoryRepository productCategoryRepository;
    private final ProductCategory productCategory = ProductCategory.builder().id(1L).name("cat1").build();
    private final List<ProductCategory> productCategories = Collections.singletonList(productCategory);

    @Before
    public void init() {
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void getRateByIdTest(){
        //given

        //when
        var mockito = when(productCategoryRepository.getReferenceById(anyLong()));

        //then
        mockito.thenReturn(productCategory);

        ProductCategory result = productCategoryRepository.getReferenceById(1L);
        assertEquals(result, productCategory);
    }

    @Test
    public void getAllRatesTest(){
        //given

        //when
        var mockito = when(productCategoryRepository.findAll());

        //then
        mockito.thenReturn(productCategories);

        List<ProductCategory> result = productCategoryRepository.findAll();
        assertEquals(result.get(0), productCategory);
    }

    @Test
    public void deleteRateTest(){
        //given

        //when

        //then

        productCategoryRepository.delete(productCategory);
        verify(productCategoryRepository).delete(productCategory);
    }
}

