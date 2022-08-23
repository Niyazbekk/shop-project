package com.example.shopproject;

import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.entity.dto.CommentDto;
import com.example.shopproject.entity.dto.ProductCategoryDto;
import com.example.shopproject.entity.dto.ProductDto;
import com.example.shopproject.entity.dto.RateDto;
import com.example.shopproject.service.BasketServiceImpl;
import com.example.shopproject.service.CommentServiceImpl;
import com.example.shopproject.service.ProductCategoryServiceImpl;
import com.example.shopproject.service.ProductServiceImpl;
import com.example.shopproject.service.RateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AdminControllerTest {
    static ProductDto productDto = ProductDto.builder().id(3L).name("product").description("sd").cost(200.0).productCategoryId(1L).build();
    static RateDto rateDto = RateDto.builder().id(23L).userId(1L).productId(1L).rating(3.0).build();
    static CommentDto commentDto = CommentDto.builder().id(23L).userId(1L).productId(1L).content("123").build();
    static ProductCategoryDto productCategoryDto = ProductCategoryDto.builder().id(1L).name("cat1").build();
    static BasketDto basketDto = BasketDto.builder().id(1L).userId(1L).productId(4L).build();


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    ProductServiceImpl productService;

    @MockBean
    BasketServiceImpl basketService;

    @MockBean
    ProductCategoryServiceImpl productCategoryService;

    @MockBean
    RateServiceImpl rateService;

    @MockBean
    CommentServiceImpl commentService;


    @Test
    void postTestProduct() throws Exception{
        Mockito.when(productService.createProduct(Mockito.any(ProductDto.class))).thenReturn(productDto);

        MvcResult result = mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
        Assertions.assertThat(json).isEqualToIgnoringCase(mapper.writeValueAsString(productDto));
    }

    @Test
    void postTestRate() throws Exception{
        Mockito.when(rateService.createRate(Mockito.any(RateDto.class))).thenReturn(rateDto);

        MvcResult result = mockMvc.perform(post("/api/v1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(rateDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
        Assertions.assertThat(json).isEqualToIgnoringCase(mapper.writeValueAsString(rateDto));
    }

    @Test
    public void postTestCategory() throws Exception {
        Mockito.when(productCategoryService.createProductCategory(Mockito.any(ProductCategoryDto.class))).thenReturn(productCategoryDto);

        MvcResult result = mockMvc.perform(post("/api/v1/product-cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productCategoryDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
        Assertions.assertThat(json).isEqualToIgnoringCase(mapper.writeValueAsString(productCategoryDto));
    }

    @Test
    public void postTestBasket() throws Exception {
        Mockito.when(basketService.createBasket(Mockito.any(BasketDto.class))).thenReturn(basketDto);

        MvcResult result = mockMvc.perform(post("/api/v1/basket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(basketDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
        Assertions.assertThat(json).isEqualToIgnoringCase(mapper.writeValueAsString(basketDto));
    }

    @Test
    void postTestComment() throws Exception{
        Mockito.when(commentService.createComment(Mockito.any(CommentDto.class))).thenReturn(commentDto);

        MvcResult result = mockMvc.perform(post("/api/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commentDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
        Assertions.assertThat(json).isEqualToIgnoringCase(mapper.writeValueAsString(commentDto));
    }

    @Test
    void postBadCategory() throws Exception {
        this.mockMvc.perform(post("/api/v1/product-cat")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void postBadProduct() throws Exception {
        this.mockMvc.perform(post("/api/v1/product")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void postBadBasket() throws Exception {
        this.mockMvc.perform(post("/api/v1/basket")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProductById() throws Exception {
        Mockito.when(productService.getProductById(3L)).thenReturn(productDto);

        MvcResult result2 = mockMvc.perform(get("http://localhost:8080/api/v1/product/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result2).isNotNull();
        String userJson2 = result2.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(productDto));
    }

    @Test
    void getBasketById() throws Exception {
        Mockito.when(basketService.getBasketById(1L)).thenReturn(basketDto);

        MvcResult result = mockMvc.perform(get("/api/v1/basket/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(basketDto));
    }

    @Test
    void getCategoryById() throws Exception {
        Mockito.when(productCategoryService.getProductCategoryById(1L)).thenReturn(productCategoryDto);

        MvcResult result = mockMvc.perform(get("/api/v1/product-cat/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(productCategoryDto));
    }

    @Test
    void getRateById() throws Exception {
        Mockito.when(rateService.getRateById(23L)).thenReturn(rateDto);

        MvcResult result = mockMvc.perform(get("/api/v1/rate/23").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(rateDto));
    }

    @Test
    void getCommentById() throws Exception {
        Mockito.when(commentService.getCommentById(23L)).thenReturn(commentDto);

        MvcResult result = mockMvc.perform(get("/api/v1/comment/23").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(commentDto));
    }


    @Test
    void deleteProduct() throws Exception {
        this.mockMvc.perform(delete("/api/v1/product/3")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteBasket() throws Exception {
        this.mockMvc.perform(delete("/api/v1/basket/1")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategory() throws Exception {
        this.mockMvc.perform(delete("/api/v1/product-cat/1")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteRate() throws Exception {
        this.mockMvc.perform(delete("/api/v1/rate/23")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteComment() throws Exception {
        this.mockMvc.perform(delete("/api/v1/comment/23")).andDo(print())
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


