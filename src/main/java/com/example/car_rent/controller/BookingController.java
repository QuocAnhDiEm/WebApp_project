package com.example.car_rent.controller;   // PHẢI CÓ "rent" Ở CUỐI

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;


@Controller
public class BookingController {

@GetMapping("/booking/form")
    public String bookingForm(
            @RequestParam("car") int carId,
            Model model,
            Authentication authentication) {

        // 🔐 Nếu chưa đăng nhập → redirect login
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }

        model.addAttribute("carId", carId);
        model.addAttribute("userEmail", authentication.getName());

        return "booking/booking-form";
    }

    @GetMapping("/booking/payment")
    public String payment(
            @RequestParam("car") int carId,
            Model model,
            Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }

        model.addAttribute("carId", carId);
        model.addAttribute("userEmail", authentication.getName());

        return "booking/payment";
    }

    @GetMapping("/booking/success")
    public String success(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        return "booking/success";
    }
}