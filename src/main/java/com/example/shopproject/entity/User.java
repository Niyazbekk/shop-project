package com.example.shopproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import java.util.Set;

@Entity
@Table(name = "_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}