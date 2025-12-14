package com.example.car_rent.controller;   // PHẢI CÓ "rent" Ở CUỐI

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {

    @GetMapping("/booking/form")
    public String bookingForm(@RequestParam("car") int carId, Model model) {
        model.addAttribute("carId", carId);
        return "booking/booking-form";
    }

    @GetMapping("/booking/payment")
    public String payment(@RequestParam("car") int carId, Model model) {
        model.addAttribute("carId", carId);
        return "booking/payment";
    }

    @GetMapping("/booking/success")
    public String success() {
        return "booking/success";
    }
}