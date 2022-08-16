package com.example.shopproject.repository;

import com.example.shopproject.entity.Basket;
import com.example.shopproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> getBasketsByUserId(Long userId);
}
