package com.example.shopproject.service;

import com.example.shopproject.entity.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceImplTest {
    //given
    final ProductDto productDto = ProductDto.builder().id(3L).name("product").description("sd").cost(200.0).productCategoryId(1L).build();
    @MockBean
    ProductServiceImpl ProductService;

    @Test
    public void updateProductTest() {
        //given

        //when
        var mockito = Mockito.when(ProductService.updateProduct(Mockito.any(ProductDto.class)));

        //then
        mockito.then(returnsFirstArg());

        ProductDto productDto1 = ProductDto.builder()
                .id(3L)
                .name("product2")
                .description("sd2")
                .cost(200.0)
                .productCategoryId(1L)
                .build();
        ProductDto result = ProductService.updateProduct(productDto1);
        assertEquals(result, productDto1);
    }

    @Test
    public void createProductTest() {
        //given

        //when
        var mockito = Mockito.when(ProductService.createProduct(Mockito.any(ProductDto.class)));

        //then
        mockito.then(returnsFirstArg());

        ProductDto result = ProductService.createProduct(productDto);
        assertEquals(result, productDto);
    }

    @Test
    public void getProductByIdTest() {
        //given

        //when
        var mockito = Mockito.when(ProductService.getProductById(Mockito.anyLong()));

        //then
        mockito.thenReturn(productDto);

        ProductDto result = ProductService.getProductById(3L);
        assertEquals(result, productDto);
    }

    @Test
    public void deleteProduct() {
        //given

        //when

        //then

        ProductService.deleteProductById(3L);
        verify(ProductService).deleteProductById(3L);
    }
}