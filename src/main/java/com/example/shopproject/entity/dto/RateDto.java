package com.example.shopproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RateDto {
    private Long id;
    private Long userId;
    private Long productId;
    private Double rating;
}
