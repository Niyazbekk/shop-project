package com.example.shopproject;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.Rate;
import com.example.shopproject.entity.User;
import com.example.shopproject.repository.RateRepository;
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
public class RateRepositoryTest {
    @MockBean
    private RateRepository RateRepository;
    static Rate rate;
    static List<Rate> rates;

    @BeforeClass
    public static void prepareTestData() {
        rate = Rate.builder().id(1L).user(new User()).product(new Product()).rating(3.0).build();
        rates = Collections.singletonList(rate);
    }

    @Before
    public void init() {
        RateRepository.save(rate);
    }

    @Test
    public void getRateByIdTest(){
        when(RateRepository.getReferenceById(anyLong())).thenReturn(rate);
        Rate result = RateRepository.getReferenceById(1L);
        assertNotNull(result);
        assertEquals(result.getId(), rate.getId());
        assertEquals(result.getUser().getId(), rate.getUser().getId());
        assertEquals(result.getProduct().getId(), rate.getProduct().getId());
        assertEquals(result.getRating(), rate.getRating());
    }

    @Test
    public void getAllRatesTest(){
        when(RateRepository.findAll()).thenReturn(rates);
        List<Rate> result = RateRepository.findAll();
        System.out.println(result);
        assertNotNull(result);
        assertEquals(result.get(0).getId(), rate.getId());
        assertEquals(result.get(0).getUser().getId(), rate.getUser().getId());
        assertEquals(result.get(0).getProduct().getId(), rate.getProduct().getId());
        assertEquals(result.get(0).getRating(), rate.getRating());
    }

    @Test
    public void deleteRateTest(){
        RateRepository.delete(rate);
        verify(RateRepository).delete(rate);
    }
}

