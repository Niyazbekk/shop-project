package com.example.shopproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double cost;
    private Long productCategoryId;
}
