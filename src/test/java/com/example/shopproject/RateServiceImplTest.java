package com.example.shopproject;

import com.example.shopproject.entity.dto.RateDto;
import com.example.shopproject.service.RateServiceImpl;
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
public class RateServiceImplTest {
    static RateDto rateDto = RateDto.builder().id(1L).userId(1L).productId(1L).build();
    @MockBean
    RateServiceImpl rateService;

    @Test
    public void updateRateTest() {
        Mockito.when(rateService.updateRate(Mockito.any(RateDto.class))).then(returnsFirstArg());
        RateDto rateDto1 = RateDto
                .builder()
                .id(1L)
                .userId(1L)
                .productId(2L)
                .build();
        RateDto result = rateService.updateRate(rateDto1);
        assertNotNull(result);
        assertSame(result.getId(), rateDto1.getId());
        assertEquals(result.getRating(), rateDto1.getRating());
        assertEquals(result.getUserId(), rateDto1.getUserId());
        assertEquals(result.getProductId(), rateDto1.getProductId());
    }

    @Test
    public void createRateTest() {
        Mockito.when(rateService.createRate(Mockito.any(RateDto.class))).then(returnsFirstArg());
        RateDto result = rateService.createRate(rateDto);
        assertNotNull(result);
        assertSame(result.getId(), rateDto.getId());
        assertEquals(result.getRating(), rateDto.getRating());
        assertEquals(result.getUserId(), rateDto.getUserId());
        assertEquals(result.getProductId(), rateDto.getProductId());
    }

    @Test
    public void getRateByIdTest() {
        Mockito.when(rateService.getRateById(Mockito.anyLong())).thenReturn(rateDto);
        RateDto result = rateService.getRateById(1L);
        assertNotNull(result);
        assertSame(result.getId(), rateDto.getId());
        assertEquals(result.getRating(), rateDto.getRating());
        assertEquals(result.getUserId(), rateDto.getUserId());
        assertEquals(result.getProductId(), rateDto.getProductId());
    }

    @Test
    public void deleteRate() {
        rateService.deleteRateById(1L);
        verify(rateService).deleteRateById(1L);
    }
}