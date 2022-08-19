package com.example.shopproject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.shopproject.entity.ProductCategory;
import com.example.shopproject.entity.dto.CommentDto;
import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.entity.dto.RateDto;
import com.example.shopproject.service.BasketServiceImpl;
import com.example.shopproject.service.ProductCategoryServiceImpl;
import com.example.shopproject.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @MockBean
    BasketServiceImpl basketService;

    @MockBean
    ProductCategoryServiceImpl productCategoryService;

    @Test
    void postTestProduct() throws Exception{
        ProductDto product = new ProductDto(4L , "product Test" , "new product" , 2000.0 , 1L);

        this.mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postTestRate() throws Exception{
        RateDto rate = new RateDto(23L , 1L , 1L ,3.0);

        this.mockMvc.perform(post("/api/v1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(rate)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postTestComment() throws Exception{
        CommentDto commentDto = new CommentDto(23L , 1L , 1L ,"test comment");

        this.mockMvc.perform(post("/api/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commentDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getProducts() throws Exception {
        this.mockMvc.perform(get("/api/v1/products")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getFirstProduct() throws Exception {
        this.mockMvc.perform(get("/api/v1/product/1")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getBaskets() throws Exception {
        this.mockMvc.perform(get("/api/v1/baskets")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postBadProduct() throws Exception {
        this.mockMvc.perform(post("/api/v1/product")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteProduct() throws Exception {
        this.mockMvc.perform(delete("/api/v1/product/1")).andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void postBadBasket() throws Exception {
        this.mockMvc.perform(post("/api/v1/basket")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteBasket() throws Exception {
        this.mockMvc.perform(delete("/api/v1/basket/1")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getCategories() throws Exception {
        this.mockMvc.perform(get("/api/v1/product-cats")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getFirstCategory() throws Exception {
        this.mockMvc.perform(get("/api/v1/product-cat/1")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addCategory() throws Exception {

        ProductCategory productCategory = new ProductCategory(2L,"category2");

        this.mockMvc.perform(post("/api/v1/product-cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productCategory)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postBadCategory() throws Exception {
        this.mockMvc.perform(post("/api/v1/product-cat")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteCategory() throws Exception {
        this.mockMvc.perform(delete("/api/v1/product-cat/1")).andDo(print())
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


