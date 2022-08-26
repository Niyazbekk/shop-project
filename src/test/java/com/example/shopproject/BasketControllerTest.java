package com.example.shopproject;

import com.example.shopproject.controller.BasketController;
import com.example.shopproject.entity.dto.BasketDto;
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
public class BasketControllerTest {
    static BasketDto basketDto = BasketDto.builder().id(3L).userId(1L).productId(1L).build();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    BasketController basketController;

    @Test
    void postTestBasket() throws Exception {
        Mockito.when(basketController.addToBasket(basketDto)).thenReturn(basketDto);

        MvcResult result = mockMvc.perform(post("/api/v1/basket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(basketDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
    }

    @Test
    void getBasketById() throws Exception {
        Mockito.when(basketController.getBasketById(3L)).thenReturn(basketDto);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/basket/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(basketDto));
    }

    @Test
    void getBaskets() throws Exception {
        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/baskets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString().trim();
        userJson2 = StringUtils.substringBetween(userJson2, "[", "]");
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(asJsonString(basketDto));
    }

    @Test
    void deleteBasket() throws Exception {
        this.mockMvc.perform(delete("/api/v1/basket/3")).andDo(print())
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

