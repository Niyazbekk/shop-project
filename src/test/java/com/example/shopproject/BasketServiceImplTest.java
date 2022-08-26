package com.example.shopproject;


import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.service.BasketServiceImpl;
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
public class BasketServiceImplTest {
    static BasketDto basketDto = BasketDto.builder().id(3L).userId(1L).productId(1L).build();
    @MockBean
    BasketServiceImpl basketService;

    @Test
    public void updateBasketTest() {
        Mockito.when(basketService.updateBasket(Mockito.any(BasketDto.class))).then(returnsFirstArg());
        BasketDto basketForUpdate = BasketDto
                .builder()
                .id(3L)
                .userId(1L)
                .productId(3L)
                .build();
        BasketDto result = basketService.updateBasket(basketForUpdate);
        assertNotNull(result);
        assertSame(result.getId(), basketForUpdate.getId());
        assertEquals(result.getUserId(), basketForUpdate.getUserId());
        assertEquals(result.getProductId(), basketForUpdate.getProductId());
    }

    @Test
    public void createBasketTest() {
        Mockito.when(basketService.createBasket(Mockito.any(BasketDto.class))).then(returnsFirstArg());
        BasketDto result = basketService.createBasket(basketDto);
        assertNotNull(result);
        assertSame(result.getId(), basketDto.getId());
        assertEquals(result.getUserId(), basketDto.getUserId());
        assertEquals(result.getProductId(), basketDto.getProductId());
    }

    @Test
    public void getBasketByIdTest() {
        Mockito.when(basketService.getBasketById(Mockito.anyLong())).thenReturn(basketDto);
        BasketDto result = basketService.getBasketById(3L);
        assertNotNull(result);
        assertSame(result.getId(), basketDto.getId());
        assertEquals(result.getUserId(), basketDto.getUserId());
        assertEquals(result.getProductId(), basketDto.getProductId());
    }

    @Test
    public void deleteBasket() {
        basketService.deleteBasketById(3L);
        verify(basketService).deleteBasketById(3L);
    }
}