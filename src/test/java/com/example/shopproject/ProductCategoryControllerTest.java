package com.example.shopproject;

import com.example.shopproject.controller.ProductCategoryController;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductCategoryControllerTest {
    static ProductCategoryDto productCategoryDto = ProductCategoryDto.builder().id(4L).name("ProductCategory 1").build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductCategoryController productCategoryController;

    @Test
    void postTestProductCategory() throws Exception {
        Mockito.when(productCategoryController.createProductCategory(productCategoryDto)).thenReturn(productCategoryDto);

        MvcResult result = mockMvc.perform(post("/api/v1/product-cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productCategoryDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
    }

    @Test
    void getProductCategoryById() throws Exception {
        Mockito.when(productCategoryController.getProductCategoryById(4L)).thenReturn(productCategoryDto);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/product-cat/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(asJsonString(productCategoryDto));
    }

    @Test
    void getProductCategories() throws Exception {
        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/product-cats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString().trim();
        userJson2 = StringUtils.substringBetween(userJson2, "[", "]");
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(asJsonString(productCategoryDto));
    }

    @Test
    void deleteProductCategory() throws Exception {
        this.mockMvc.perform(delete("/api/v1/product-cat/4")).andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}