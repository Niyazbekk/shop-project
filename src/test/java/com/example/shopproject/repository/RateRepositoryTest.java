package com.example.shopproject.repository;

import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.Rate;
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
public class RateRepositoryTest {
    //given
    @MockBean
    private RateRepository RateRepository;
    private final Rate rate = Rate.builder().id(1L).user(new User()).product(new Product()).rating(3.0).build();
    private final List<Rate> rates = Collections.singletonList(rate);

    @Before
    public void init() {
        RateRepository.save(rate);
    }

    @Test
    public void getRateByIdTest(){
        //given

        //when
        var mockito = when(RateRepository.getReferenceById(anyLong()));

        //then
        mockito.thenReturn(rate);

        Rate result = RateRepository.getReferenceById(1L);
        assertEquals(result, rate);
    }

    @Test
    public void getAllRatesTest(){
        //given

        //when
        var mockito = when(RateRepository.findAll());

        //then
        mockito.thenReturn(rates);

        List<Rate> result = RateRepository.findAll();
        assertEquals(result.get(0), rate);
    }

    @Test
    public void deleteRateTest(){
        //given

        //when

        //then

        RateRepository.delete(rate);
        verify(RateRepository).delete(rate);
    }
}

