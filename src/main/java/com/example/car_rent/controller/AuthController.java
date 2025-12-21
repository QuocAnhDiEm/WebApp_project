package com.example.car_rent.controller;

import com.example.car_rent.entity.User;
import com.example.car_rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }
     

    @GetMapping("auth/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/auth/register";
    }

    @PostMapping("/auth/register")
    public String registerUser(User user) {
        user.setRole("ROLE_USER");
        userService.save(user);
        return "redirect:/auth/login";
    }
}
