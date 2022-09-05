package com.example.shopproject.repository;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BasketRepositoryTest {
    //given
    @MockBean
    private BasketRepository basketRepository;
    private final Basket basket = Basket.builder().id(1L).user(new User()).product(new Product()).build();
    private final List<Basket> baskets = Collections.singletonList(basket);

    @Before
    public void init() {
        basketRepository.save(basket);
    }

    @Test
    public void getBasketByIdTest() {
        //given

        //when
        var mockito = when(basketRepository.getReferenceById(anyLong()));

        //then
        mockito.thenReturn(basket);

        Basket result = basketRepository.getReferenceById(1L);
        assertEquals(result, basket);
    }

    @Test
    public void getAllBasketsTest() {
        //given

        //when
        var mockito = when(basketRepository.findAll());

        //then
        mockito.thenReturn(baskets);

        List<Basket> result = basketRepository.findAll();
        System.out.println(result);
        assertEquals(result.get(0), basket);
    }

    @Test
    public void deleteBasketTest() {
        //given

        //when

        //then

        basketRepository.delete(basket);
        verify(basketRepository).delete(basket);
    }
}
