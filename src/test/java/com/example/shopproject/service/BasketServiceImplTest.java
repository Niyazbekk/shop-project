package com.example.shopproject.service;


import com.example.shopproject.entity.dto.BasketDto;
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
public class BasketServiceImplTest {
    //given
    final BasketDto basketDto = BasketDto.builder().id(3L).userId(1L).productId(1L).build();
    @MockBean
    BasketServiceImpl basketService;

    @Test
    public void updateBasketTest() {
        //given

        //when
        var mockito = Mockito.when(basketService.updateBasket(Mockito.any(BasketDto.class)));

        //then
        mockito.then(returnsFirstArg());

        BasketDto basketForUpdate = BasketDto
                .builder()
                .id(3L)
                .userId(1L)
                .productId(3L)
                .build();
        BasketDto result = basketService.updateBasket(basketForUpdate);
        assertEquals(result, basketForUpdate);
    }

    @Test
    public void createBasketTest() {
        //given

        //when
        var mockito = Mockito.when(basketService.createBasket(Mockito.any(BasketDto.class)));

        //then
        mockito.then(returnsFirstArg());

        BasketDto result = basketService.createBasket(basketDto);
        assertEquals(result, basketDto);
    }

    @Test
    public void getBasketByIdTest() {
        //given

        //when
        var mockito = Mockito.when(basketService.getBasketById(Mockito.anyLong()));

        //then
        mockito.thenReturn(basketDto);

        BasketDto result = basketService.getBasketById(3L);
        assertEquals(result, basketDto);
    }

    @Test
    public void deleteBasket() {
        //given

        //when

        //then

        basketService.deleteBasketById(3L);
        verify(basketService).deleteBasketById(3L);
    }
}