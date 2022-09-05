package com.example.shopproject.controller;

import com.example.shopproject.entity.dto.ProductDto;
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
public class ProductControllerTest {
    //given
    private final ProductDto productDto = ProductDto.builder().id(6L).name("product1").description("nice product").cost(5000.0).productCategoryId(1L).build();
    private final List<ProductDto> products = Collections.singletonList(productDto);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    ProductController productController;

    @Test
    void postTestProduct() throws Exception {
        //given

        //when
        var mockito = Mockito.when(productController.createProduct(productDto));

        //then
        mockito.thenReturn(productDto);

        MvcResult result = mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(productDto));
    }

    @Test
    void getProductById() throws Exception {
        //given

        //when
        var mockito = Mockito.when(productController.getProductById(6L));

        //then
        mockito.thenReturn(productDto);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/product/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(productDto));
    }

    @Test
    void getProducts() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 5);
        Page<ProductDto> page = new PageImpl<>(products, pageable, products.size());

        //when
        var mockito = Mockito.when(productController.getAllProducts(pageable));

        //then
        mockito.thenReturn(page);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString().trim();
        userJson2 = StringUtils.substringBetween(userJson2, "[", "]");
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(productDto));
    }

    @Test
    void deleteProduct() throws Exception {
        //given

        //when

        //then

        this.mockMvc.perform(delete("/api/v1/product/6")).andDo(print())
                .andExpect(status().isOk());
    }
}