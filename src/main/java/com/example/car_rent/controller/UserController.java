package com.example.car_rent.controller;

import com.example.car_rent.entity.User;
import com.example.car_rent.service.BookingService;
import com.example.car_rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard/my-bookings")
    public String myBookings(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("bookings", bookingService.getBookingsByUser(user));

        return "dashboard/my-bookings";
    }
}
