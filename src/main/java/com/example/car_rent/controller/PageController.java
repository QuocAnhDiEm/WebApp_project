package com.example.car_rent.controller;   // PHẢI CÓ "rent" Ở CUỐI

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/cars")
    public String cars() {
        return "cars";
    }
}