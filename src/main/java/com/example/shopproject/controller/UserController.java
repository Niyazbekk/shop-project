package com.example.shopproject.controller;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.Product;
import com.example.shopproject.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    BasketService basketService;

    @PostMapping("/basket")
    public Basket addToBasket(@Valid @RequestBody Basket basket) {
        return basketService.createBasket(basket);
    }

    @GetMapping("/basket/user/{userID}")
    public List<Basket> getAllBasketsBy(@PathVariable Long userID){
        return basketService.getAllBasketsByUserId(userID);
    }

    @GetMapping("/basket/{basketID}")
    public Basket getBasketById(@PathVariable Long basketID) {
        return basketService.getBasketById(basketID);
    }

    @PutMapping("/basket")
    public Basket updateProductById(@Valid @RequestBody Basket basket) {
        return basketService.updateBasketById(basket);
    }

    @DeleteMapping("/basket/{basketID}")
    public ResponseEntity<String> deleteBasketById(@PathVariable Long basketID) {
        basketService.deleteBasketById(basketID);
        return new ResponseEntity<>("Успешно удален", HttpStatus.OK);
    }
}
