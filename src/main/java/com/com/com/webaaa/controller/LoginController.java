package com.com.com.webaaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getRoot() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login/login";
    }

    @PostMapping("/login")
    public String postLogin(Model model) {
        return "redirect:home";
    }
}
