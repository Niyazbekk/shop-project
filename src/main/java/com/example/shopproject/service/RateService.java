package com.example.shopproject.service;

import com.example.shopproject.entity.Rate;
import com.example.shopproject.entity.dto.RateDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RateService {
    RateDto createRate(RateDto rate);
    List<Rate> getAllRates(Pageable pageable);
    RateDto getRateById(Long id);
    RateDto updateRate(RateDto rate);
    void deleteRateById(Long id);
}
