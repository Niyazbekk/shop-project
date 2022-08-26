package com.example.shopproject;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.repository.ProductRepository;
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
public class ProductRepositoryTest {
    @MockBean
    private ProductRepository productRepository;
    static Product product;
    static List<Product> products;

    @BeforeClass
    public static void prepareTestData() {
        product = Product.builder().id(1L).name("product").description("desc").cost(2000.0).productCategory(new ProductCategory()).build();
        products = Collections.singletonList(product);
    }

    @Before
    public void init() {
        productRepository.save(product);
    }

    @Test
    public void getProductByIdTest() {
        when(productRepository.getReferenceById(anyLong())).thenReturn(product);
        Product result = productRepository.getReferenceById(1L);
        assertNotNull(result);
        assertEquals(result.getId(), product.getId());
        assertEquals(result.getCost(), product.getCost());
        assertEquals(result.getDescription(), product.getDescription());
        assertEquals(result.getName(), product.getName());
    }

    @Test
    public void getAllProductsTest() {
        productRepository.save(product);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productRepository.findAll();
        System.out.println(result);
        assertNotNull(result);
        assertEquals(result.get(0).getId(), product.getId());
        assertEquals(result.get(0).getCost(), product.getCost());
        assertEquals(result.get(0).getDescription(), product.getDescription());
        assertEquals(result.get(0).getName(), product.getName());
    }

    @Test
    public void deleteProductTest() {
        productRepository.delete(product);
        verify(productRepository).delete(product);
    }
}
