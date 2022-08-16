package com.example.shopproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {
    private Long id;
    private Long userId;
    private Long productId;
}
