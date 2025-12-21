package com.example.car_rent.controller;

import com.example.car_rent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    // Show all available cars
    @GetMapping("/cars")
    public String cars(Model model) {
        model.addAttribute("cars", carService.getAvailableCars());
        return "cars";
    }

    // Show booking form
    @GetMapping("/cars/book/{id}")
    public String bookingForm(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "car/booking-form";
    }
}
