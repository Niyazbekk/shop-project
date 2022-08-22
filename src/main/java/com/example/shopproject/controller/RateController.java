package com.example.shopproject.controller;

import com.example.shopproject.entity.Rate;
import com.example.shopproject.entity.dto.RateDto;
import com.example.shopproject.service.RateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateController.class);
    private final RateService rateService;

    @PostMapping("/rate")
    public RateDto createRate(@Valid @RequestBody RateDto rate) {
        LOGGER.info("create rate");
        return rateService.createRate(rate);
    }

    @GetMapping("/rates")
    public Page<Rate> getAllRates(@PageableDefault(value = 5, page = 0) Pageable pageable){
        LOGGER.info("get all rates");
        return rateService.getAllRates(pageable);
    }

    @GetMapping("/rate/{id}")
    public RateDto getRateById(@PathVariable Long id) {
        LOGGER.info("get rate by id");
        return rateService.getRateById(id);
    }

    @PutMapping("/rate")
    public RateDto updateRate(@Valid @RequestBody RateDto rate) {
        LOGGER.info("update");
        return rateService.updateRate(rate);
    }

    @DeleteMapping("/rate/{id}")
    public void deleteRateById(@PathVariable Long id) {
        LOGGER.info("delete");
        rateService.deleteRateById(id);
    }
}
