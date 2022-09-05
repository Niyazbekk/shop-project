package com.example.shopproject.service;

import com.example.shopproject.entity.Role;
import com.example.shopproject.entity.User;
import com.example.shopproject.entity.dto.UserPrincipal;
import com.example.shopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
    public List<User> allUsers() {
        return userRepository.findAll();
    }
    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return true;
    }
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
    public String checkUserRegistration(User user){
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            return "Пароли не совпадают";
        }
        if (!saveUser(user)){
            return "Пользователь с таким именем уже существует";
        }
        return "Вы зарегистрировались";
    }
}