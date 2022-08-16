package com.example.shopproject.service;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BasketService {
    @Autowired
    BasketRepository basketRepository;

    public Basket createBasket(Basket basket){
        return basketRepository.save(basket);
    };

    public List<Basket> getAllBasketsByUserId(Long user_id){
        return basketRepository.getBasketsByUserID(user_id);
    };

    public Basket getBasketById(Long id){
        return basketRepository.getBasketById(id);
    };

    public Basket updateBasketById(Basket basket){
        Basket basket1 = basketRepository.getBasketById(basket.getId());
        basket.setId(basket1.getId());
        basket = basketRepository.save(basket);

        return basket;
    };

    public void deleteBasketById(Long id){
        basketRepository.deleteBasketById(id);
    };
}
