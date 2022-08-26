package com.example.shopproject.controller;

import com.example.shopproject.entity.dto.RateDto;
import com.example.shopproject.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class RateController {
    private final RateService rateService;
    @PostMapping("/rate")
    public RateDto createRate(@Valid @RequestBody RateDto rate) {
        log.info("Rest request to create rate");
        return rateService.createRate(rate);
    }
    @GetMapping("/rates")
    public Page<RateDto> getAllRates(@PageableDefault(value = 5, page = 0) Pageable pageable){
        log.info("Rest request to get all rates by page with page size = {} and page number = {}",pageable.getPageSize() , pageable.getPageNumber());
        return rateService.getAllRates(pageable);
    }
    @GetMapping("/rate/{id}")
    public RateDto getRateById(@PathVariable Long id) {
        log.info("Rest request to get rate by id = {}", id);
        return rateService.getRateById(id);
    }
    @PutMapping("/rate")
    public RateDto updateRate(@Valid @RequestBody RateDto rate) {
        log.info("Rest request to update rate with userId = {} " ,rate.getUserId());
        return rateService.updateRate(rate);
    }
    @DeleteMapping("/rate/{id}")
    public void deleteRateById(@PathVariable Long id) {
        log.info("Rest request to delete rate by id = {}" , id);
        rateService.deleteRateById(id);
    }
}