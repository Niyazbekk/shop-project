package com.example.shopproject.controller;

import com.example.shopproject.entity.dto.RateDto;
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
public class RateControllerTest {
    //given
    private final RateDto rateDto = RateDto.builder().id(1L).userId(1L).productId(1L).build();
    private final List<RateDto> rates = Collections.singletonList(rateDto);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    RateController rateController;

    @Test
    void postTestRate() throws Exception {
        //given

        //when
        var mockito = Mockito.when(rateController.createRate(rateDto));

        //then
        mockito.thenReturn(rateDto);

        MvcResult result = mockMvc.perform(post("/api/v1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rateDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(rateDto));
    }

    @Test
    void getRateById() throws Exception {
        //given

        //when
        var mockito = Mockito.when(rateController.getRateById(1L));

        //then
        mockito.thenReturn(rateDto);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/rate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(rateDto));
    }

    @Test
    void getRates() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 5);
        Page<RateDto> page = new PageImpl<>(rates, pageable, rates.size());

        //when
        var mockito = Mockito.when(rateController.getAllRates(pageable));

        //then
        mockito.thenReturn(page);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/rates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString().trim();
        userJson2 = StringUtils.substringBetween(userJson2, "[", "]");
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(mapper.writeValueAsString(rateDto));
    }

    @Test
    void deleteRate() throws Exception {
        //given

        //when

        //then

        this.mockMvc.perform(delete("/api/v1/rate/1")).andDo(print())
                .andExpect(status().isOk());
    }
}