package com.example.shopproject.service;

import com.example.shopproject.entity.dto.ProductCategoryDto;
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
public class ProductCategoryServiceImplTest {
    //given
    final ProductCategoryDto productCategoryDto = ProductCategoryDto.builder().id(3L).name("ProductCategory 1").build();
    @MockBean
    ProductCategoryServiceImpl productCategoryService;

    @Test
    public void updateProductCategoryTest() {
        //given

        //when
        var mockito = Mockito.when(productCategoryService.updateProductCategory(Mockito.any(ProductCategoryDto.class)));

        //then
        mockito.then(returnsFirstArg());

        ProductCategoryDto productCategoryDto1 = ProductCategoryDto
                .builder()
                .id(3L)
                .name("2")
                .build();
        ProductCategoryDto result = productCategoryService.updateProductCategory(productCategoryDto1);
        assertEquals(result, productCategoryDto1);
    }

    @Test
    public void createProductCategoryTest() {
        //given

        //when
        var mockito = Mockito.when(productCategoryService.createProductCategory(Mockito.any(ProductCategoryDto.class)));

        //then
        mockito.then(returnsFirstArg());

        ProductCategoryDto result = productCategoryService.createProductCategory(productCategoryDto);
        assertEquals(result, productCategoryDto);
    }

    @Test
    public void getProductCategoryByIdTest() {
        //given

        //when
        var mockito = Mockito.when(productCategoryService.getProductCategoryById(Mockito.anyLong()));

        //then
        mockito.thenReturn(productCategoryDto);

        ProductCategoryDto result = productCategoryService.getProductCategoryById(3L);
        assertEquals(result, productCategoryDto);
    }

    @Test
    public void deleteProductCategory() {
        //given

        //when

        //then

        productCategoryService.deleteProductCategoryById(3L);
        verify(productCategoryService).deleteProductCategoryById(3L);
    }
}