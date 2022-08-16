package com.example.shopproject.repository;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> getBasketsByUserID(Long user_id);
    Basket getBasketById(Long id);
    void deleteBasketById(Long id);
}
