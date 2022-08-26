package com.example.shopproject.controller;

import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.service.BasketService;
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
public class BasketController {
    private final BasketService basketService;
    @PostMapping("/basket")
    public BasketDto addToBasket(@Valid @RequestBody BasketDto basketDto) {
        log.info("Rest request to add to basket");
        return basketService.createBasket(basketDto);
    }
    @GetMapping("/baskets")
    public Page<BasketDto> getAllBaskets(@PageableDefault(value = 5, page = 0) Pageable pageable){
        log.info("Rest request to get all baskets by pages with page size = {} and page number = {}",pageable.getPageSize() , pageable.getPageNumber());
        return basketService.getAllBaskets(pageable);
    }
    @GetMapping("/basket/{id}")
    public BasketDto getBasketById(@PathVariable Long id) {
        log.info("Rest request to get basket by id = {}",id);
        return basketService.getBasketById(id);
    }
    @PutMapping("/basket")
    public BasketDto updateBasket(@Valid @RequestBody BasketDto basketDto) {
        log.info("Rest request to update basket with userId = {}",basketDto.getUserId());
        return basketService.updateBasket(basketDto);
    }
    @DeleteMapping("/basket/{id}")
    public void deleteBasketById(@PathVariable Long id) {
        log.info("Rest request to delete basket by id = {}",id);
        basketService.deleteBasketById(id);
    }
}