package com.example.shopproject;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.User;
import com.example.shopproject.repository.BasketRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BasketRepositoryTest {
    @MockBean
    private BasketRepository basketRepository;
    static Basket basket;
    static List<Basket> baskets;

    @BeforeClass
    public static void prepareTestData() {
        basket = Basket.builder().id(1L).user(new User()).product(new Product()).build();
        baskets = Collections.singletonList(basket);
    }

    @Before
    public void init() {
        basketRepository.save(basket);
    }

    @Test
    public void getBasketByIdTest() {
        when(basketRepository.getReferenceById(anyLong())).thenReturn(basket);
        Basket result = basketRepository.getReferenceById(1L);
        assertNotNull(result);
        assertEquals(result.getId(), basket.getId());
        assertEquals(result.getUser(), basket.getUser());
        assertEquals(result.getProduct(), basket.getProduct());
    }

    @Test
    public void getAllBasketsTest() {
        basketRepository.save(basket);
        when(basketRepository.findAll()).thenReturn(baskets);
        List<Basket> result = basketRepository.findAll();
        System.out.println(result);
        assertNotNull(result);
        assertEquals(result.get(0).getId(), basket.getId());
        assertEquals(result.get(0).getProduct(), basket.getProduct());
        assertEquals(result.get(0).getUser(), basket.getUser());
    }

    @Test
    public void deleteBasketTest() {
        basketRepository.delete(basket);
        verify(basketRepository).delete(basket);
    }
}
