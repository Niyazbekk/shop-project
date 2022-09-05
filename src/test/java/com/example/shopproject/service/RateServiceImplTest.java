package com.example.shopproject.service;

import com.example.shopproject.entity.dto.RateDto;
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
public class RateServiceImplTest {
    //given
    final RateDto rateDto = RateDto.builder().id(1L).userId(1L).productId(1L).build();
    @MockBean
    RateServiceImpl rateService;

    @Test
    public void updateRateTest() {
        //given

        //when
        var mockito = Mockito.when(rateService.updateRate(Mockito.any(RateDto.class)));

        //then
        mockito.then(returnsFirstArg());

        RateDto rateDto1 = RateDto
                .builder()
                .id(1L)
                .userId(1L)
                .productId(2L)
                .build();
        RateDto result = rateService.updateRate(rateDto1);
        assertEquals(result, rateDto1);
    }

    @Test
    public void createRateTest() {
        //given

        //when
        var mockito = Mockito.when(rateService.createRate(Mockito.any(RateDto.class)));

        //then
        mockito.then(returnsFirstArg());

        RateDto result = rateService.createRate(rateDto);
        assertEquals(result, rateDto);
    }

    @Test
    public void getRateByIdTest() {
        //given

        //when
        var mockito = Mockito.when(rateService.getRateById(Mockito.anyLong()));

        //then
        mockito.thenReturn(rateDto);

        RateDto result = rateService.getRateById(1L);
        assertEquals(result, rateDto);
    }

    @Test
    public void deleteRate() {
        //given

        //when

        //then

        rateService.deleteRateById(1L);
        verify(rateService).deleteRateById(1L);
    }
}