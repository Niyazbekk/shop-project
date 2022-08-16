package com.example.shopproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }
}
