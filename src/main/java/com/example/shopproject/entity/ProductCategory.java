package com.example.shopproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
