package com.example.car_rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "home";                    // → trả về home.html
    }

    @GetMapping("/cars")
    public String cars() {
        return "cars";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/booking/form")
    public String bookingForm() {
        return "booking/booking-form";
    }

    @GetMapping("/booking/payment")
    public String payment() {
        return "booking/payment";
    }

    @GetMapping("/booking/success")
    public String success() {
        return "booking/success";
    }

    @GetMapping("/my-bookings")
    public String myBookings() {
        return "dashboard/my-bookings";
    }
}