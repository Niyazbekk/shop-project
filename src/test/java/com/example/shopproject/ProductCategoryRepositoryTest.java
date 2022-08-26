package com.example.shopproject;

import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.repository.ProductCategoryRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {
    @MockBean
    private ProductCategoryRepository productCategoryRepository;
    static ProductCategory productCategory;
    static List<ProductCategory> productCategories;

    @BeforeClass
    public static void prepareTestData() {
        productCategory = ProductCategory.builder().id(1L).name("cat1").build();
        productCategories = Collections.singletonList(productCategory);
    }

    @Before
    public void init() {
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void getRateByIdTest(){
        when(productCategoryRepository.getReferenceById(anyLong())).thenReturn(productCategory);
        ProductCategory result = productCategoryRepository.getReferenceById(1L);
        assertNotNull(result);
        assertEquals(result.getId(), productCategory.getId());
        assertEquals(result.getName(), productCategory.getName());
    }

    @Test
    public void getAllRatesTest(){
        when(productCategoryRepository.findAll()).thenReturn(productCategories);
        List<ProductCategory> result = productCategoryRepository.findAll();
        System.out.println(result);
        assertNotNull(result);
        assertEquals(result.get(0).getId(), productCategory.getId());
        assertEquals(result.get(0).getName(), productCategory.getName());
    }

    @Test
    public void deleteRateTest(){
        productCategoryRepository.delete(productCategory);
        verify(productCategoryRepository).delete(productCategory);
    }
}

