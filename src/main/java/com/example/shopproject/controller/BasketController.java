package com.example.shopproject.controller;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BasketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasketController.class);
    private final BasketService basketService;

    @PostMapping("/basket")
    public BasketDto addToBasket(@Valid @RequestBody BasketDto basketDto) {
        LOGGER.info("addToBasket");
        return basketService.createBasket(basketDto);
    }

    @GetMapping("/baskets")
    public List<Basket> getAllBaskets(@RequestParam(defaultValue = "0") int page){
        LOGGER.info("getAllBaskets");
        PageRequest pageRequest = PageRequest.of(page, 3);
        return basketService.getAllBaskets(pageRequest);
    }

    @GetMapping("/basket/{id}")
    public BasketDto getBasketById(@PathVariable Long id) {
        LOGGER.info("getBasketById");
        return basketService.getBasketById(id);
    }

    @PutMapping("/basket")
    public BasketDto updateBasket(@Valid @RequestBody BasketDto basketDto) {
        LOGGER.info("update");
        return basketService.updateBasket(basketDto);
    }

    @DeleteMapping("/basket/{id}")
    public void deleteBasketById(@PathVariable Long id) {
        LOGGER.info("delete");
        basketService.deleteBasketById(id);
    }
}
