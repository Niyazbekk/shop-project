package com.example.shopproject.service;

import com.example.shopproject.entity.dto.BasketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BasketService {
    BasketDto createBasket(BasketDto basket);
    Page<BasketDto> getAllBaskets(Pageable pageable);
    BasketDto getBasketById(Long id);
    BasketDto updateBasket(BasketDto basket);
    void deleteBasketById(Long id);
}