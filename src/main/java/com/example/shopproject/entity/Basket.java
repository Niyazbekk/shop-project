package com.example.shopproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "basket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
}
