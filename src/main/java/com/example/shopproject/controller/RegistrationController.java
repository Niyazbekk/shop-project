package com.example.shopproject.controller;

import com.example.shopproject.entity.User;
import com.example.shopproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public String addUser(@RequestBody User user) {
        return userService.checkUserRegistration(user);
    }
}
