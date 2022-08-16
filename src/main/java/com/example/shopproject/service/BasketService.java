package com.example.shopproject.service;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.dto.BasketDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {
    BasketDto createBasket(Basket basket);
    List<Basket> getAllBasketsByUserId(Long userId);
    BasketDto getBasketById(Long id);
    BasketDto updateBasket(Basket basket);
    void deleteBasketById(Long id);
}
