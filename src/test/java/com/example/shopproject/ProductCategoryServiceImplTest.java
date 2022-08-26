package com.example.shopproject;

import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.service.ProductCategoryServiceImpl;
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
public class ProductCategoryServiceImplTest {
    static ProductCategoryDto productCategoryDto = ProductCategoryDto.builder().id(3L).name("ProductCategory 1").build();
    @MockBean
    ProductCategoryServiceImpl productCategoryService;

    @Test
    public void updateProductCategoryTest() {
        Mockito.when(productCategoryService.updateProductCategory(Mockito.any(ProductCategoryDto.class))).then(returnsFirstArg());
        ProductCategoryDto productCategoryDto1 = ProductCategoryDto
                .builder()
                .id(3L)
                .name("2")
                .build();
        ProductCategoryDto result = productCategoryService.updateProductCategory(productCategoryDto1);
        assertNotNull(result);
        assertSame(result.getId(), productCategoryDto1.getId());
        assertEquals(result.getName(), productCategoryDto1.getName());
    }

    @Test
    public void createProductCategoryTest() {
        Mockito.when(productCategoryService.createProductCategory(Mockito.any(ProductCategoryDto.class))).then(returnsFirstArg());
        ProductCategoryDto result = productCategoryService.createProductCategory(productCategoryDto);
        assertNotNull(result);
        assertSame(result.getId(), productCategoryDto.getId());
        assertEquals(result.getName(), productCategoryDto.getName());
    }

    @Test
    public void getProductCategoryByIdTest() {
        Mockito.when(productCategoryService.getProductCategoryById(Mockito.anyLong())).thenReturn(productCategoryDto);
        ProductCategoryDto result = productCategoryService.getProductCategoryById(3L);
        assertNotNull(result);
        assertSame(result.getId(), productCategoryDto.getId());
        assertEquals(result.getName(), productCategoryDto.getName());
    }

    @Test
    public void deleteProductCategory() {
        productCategoryService.deleteProductCategoryById(3L);
        verify(productCategoryService).deleteProductCategoryById(3L);
    }
}