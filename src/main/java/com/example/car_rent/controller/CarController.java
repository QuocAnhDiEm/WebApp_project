package com.example.car_rent.controller;

import com.example.car_rent.entity.Car;
import com.example.car_rent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    // =============================
    // Show cars + search + filters
    // =============================
    @GetMapping("/cars")
    public String cars(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer seats,
            Model model
    ) {
        List<Car> cars;

        if (keyword != null && !keyword.isBlank()) {
            cars = carService.searchCars(keyword);
        } else if (brand != null && !brand.isBlank()) {
            cars = carService.filterByBrand(brand);
        } else if (seats != null) {
            cars = carService.filterBySeats(seats);
        } else {
            cars = carService.getAvailableCars();
        }

        model.addAttribute("cars", cars);
        model.addAttribute("keyword", keyword);
        model.addAttribute("brand", brand);
        model.addAttribute("seats", seats);

        return "cars"; // templates/cars.html
    }

    // =============================
    // Show booking form  ✅ FIXED
    // =============================
    @GetMapping("/cars/book/{id}")
    public String bookingForm(@PathVariable Long id, Model model) {

        Car car = carService.getCarById(id);

        if (car == null) {
            return "redirect:/cars";
        }

        model.addAttribute("car", car);

        // 🔥 FIX IS HERE
        return "booking/booking-form";
        // maps to: templates/booking/booking-form.html
    }
}
