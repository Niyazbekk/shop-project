package com.example.shopproject;

import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceImplTest {
    static ProductDto productDto = ProductDto.builder().id(3L).name("product").description("sd").cost(200.0).productCategoryId(1L).build();
    @MockBean
    ProductServiceImpl ProductService;

    @Test
    public void updateProductTest() {
        Mockito.when(ProductService.updateProduct(Mockito.any(ProductDto.class))).then(returnsFirstArg());
        ProductDto productDto1 = ProductDto.builder()
                .id(3L)
                .name("product2")
                .description("sd2")
                .cost(200.0)
                .productCategoryId(1L)
                .build();
        ProductDto result = ProductService.updateProduct(productDto1);
        assertNotNull(result);
        assertSame(result.getId(), productDto1.getId());
        assertEquals(result.getName(), productDto1.getName());
        assertEquals(result.getCost(), productDto1.getCost());
        assertEquals(result.getProductCategoryId(), productDto1.getProductCategoryId());
    }

    @Test
    public void createProductTest() {
        Mockito.when(ProductService.createProduct(Mockito.any(ProductDto.class))).then(returnsFirstArg());
        ProductDto result = ProductService.createProduct(productDto);
        assertNotNull(result);
        assertSame(result.getId(), productDto.getId());
        assertEquals(result.getName(), productDto.getName());
        assertEquals(result.getCost(), productDto.getCost());
        assertEquals(result.getProductCategoryId(), productDto.getProductCategoryId());
    }

    @Test
    public void getProductByIdTest() {
        Mockito.when(ProductService.getProductById(Mockito.anyLong())).thenReturn(productDto);
        ProductDto result = ProductService.getProductById(3L);
        assertNotNull(result);
        assertSame(result.getId(), productDto.getId());
        assertEquals(result.getName(), productDto.getName());
        assertEquals(result.getCost(), productDto.getCost());
        assertEquals(result.getProductCategoryId(), productDto.getProductCategoryId());
    }

    @Test
    public void deleteProduct() {
        ProductService.deleteProductById(3L);
        verify(ProductService).deleteProductById(3L);
    }
}