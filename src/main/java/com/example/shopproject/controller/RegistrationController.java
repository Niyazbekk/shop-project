package com.example.shopproject.controller;

import com.example.shopproject.entity.User;
import com.example.shopproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            return new ResponseEntity<>("Пароли не совпадают", HttpStatus.BAD_REQUEST);

        }
        if (!userService.saveUser(user)){
            return new ResponseEntity<>("Пользователь с таким именем уже существует", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Вы зарегистрировались", HttpStatus.OK);
    }
}
