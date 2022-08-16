package com.example.shopproject.controller;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.dto.BasketDto;
import com.example.shopproject.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/basket")
    public BasketDto addToBasket(@Valid @RequestBody Basket basket) {
        return basketService.createBasket(basket);
    }

    @GetMapping("/baskets/{userId}")
    public List<Basket> getAllBasketsByUserId(@PathVariable Long userId){
        return basketService.getAllBasketsByUserId(userId);
    }

    @GetMapping("/basket/{id}")
    public BasketDto getBasketById(@PathVariable Long id) {
        return basketService.getBasketById(id);
    }

    @PutMapping("/basket")
    public BasketDto updateBasket(@Valid @RequestBody Basket basket) {
        return basketService.updateBasket(basket);
    }

    @DeleteMapping("/basket/{id}")
    public String deleteBasketById(@PathVariable Long id) {
        basketService.deleteBasketById(id);
        return "Успешно удален";
    }
}
