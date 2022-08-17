package com.example.shopproject;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.Product;
import com.example.shopproject.service.BasketServiceImpl;
import com.example.shopproject.service.ProductService;
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

    @Test
    public void addProduct() throws Exception {

        Product product = new Product(2L, "product2", "nice product2", 7000.0, 1L);

        this.mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
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
        this.mockMvc.perform(get("/api/v1/baskets/1")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postBadProduct() throws Exception {
        this.mockMvc.perform(post("/api/v1/product")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addBasket() throws Exception {

        Basket basket = new Basket(2L,1L,1L);

        this.mockMvc.perform(post("/api/v1/basket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(basket)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postBadBasket() throws Exception {
        this.mockMvc.perform(post("/api/v1/basket")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCategories() throws Exception {
        this.mockMvc.perform(get("/api/v1/product-cats")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("category1")));
    }

    @Test
    void getFirstCategory() throws Exception {
        this.mockMvc.perform(get("/api/v1/product-cat/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("category1")));
    }

    @Test
    void postBadCategory() throws Exception {
        this.mockMvc.perform(post("/api/v1/product-cat")).andDo(print())
                .andExpect(status().isBadRequest());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}


