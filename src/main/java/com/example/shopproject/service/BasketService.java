package com.example.shopproject.service;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.dto.BasketDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {
    BasketDto createBasket(BasketDto basket);
    List<Basket> getAllBaskets(Pageable pageable);
    BasketDto getBasketById(Long id);
    BasketDto updateBasket(BasketDto basket);
    void deleteBasketById(Long id);
}
