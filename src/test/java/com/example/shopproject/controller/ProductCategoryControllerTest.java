package com.example.shopproject.controller;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductCategoryControllerTest {
    //given
    private final ProductCategoryDto productCategoryDto = ProductCategoryDto.builder().id(4L).name("ProductCategory 1").build();
    private final List<ProductCategoryDto> productCategories = Collections.singletonList(productCategoryDto);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    ProductCategoryController productCategoryController;

    @Test
    void postTestProductCategory() throws Exception {
        //given

        //when
        var mockito = Mockito.when(productCategoryController.createProductCategory(productCategoryDto));

        //then
        mockito.thenReturn(productCategoryDto);

        MvcResult result = mockMvc.perform(post("/api/v1/product-cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productCategoryDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(productCategoryDto));
    }

    @Test
    void getProductCategoryById() throws Exception {
        //given

        //when
        var mockito = Mockito.when(productCategoryController.getProductCategoryById(4L));

        //then
        mockito.thenReturn(productCategoryDto);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/product-cat/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(productCategoryDto));
    }

    @Test
    void getProductCategories() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 5);
        Page<ProductCategoryDto> page = new PageImpl<>(productCategories, pageable, productCategories.size());

        //when
        var mockito = Mockito.when(productCategoryController.getAllProductCategories(pageable));

        //then
        mockito.thenReturn(page);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/product-cats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        String userJson2 = result.getResponse().getContentAsString().trim();
        userJson2 = StringUtils.substringBetween(userJson2, "[", "]");
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(productCategoryDto));
    }

    @Test
    void deleteProductCategory() throws Exception {
        //given

        //when

        //then

        this.mockMvc.perform(delete("/api/v1/product-cat/4")).andDo(print())
                .andExpect(status().isOk());
    }
}