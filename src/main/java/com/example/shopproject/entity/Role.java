package com.example.shopproject.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
