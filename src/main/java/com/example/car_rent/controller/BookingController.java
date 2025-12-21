package com.example.car_rent.controller;

import com.example.car_rent.dto.BookingRequestDTO;
import com.example.car_rent.entity.Booking;
import com.example.car_rent.entity.Car;
import com.example.car_rent.entity.User;
import com.example.car_rent.service.BookingService;
import com.example.car_rent.service.CarService;
import com.example.car_rent.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final CarService carService;
    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(CarService carService, BookingService bookingService, UserService userService) {
        this.carService = carService;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    // Show booking form
    @GetMapping("/form")
    public String bookingForm(@RequestParam("car") Long carId, Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }

        Car car = carService.getCarById(carId);
        if (car == null) {
            model.addAttribute("errorMessage", "Car not found!");
            return "redirect:/cars";
        }

        model.addAttribute("car", car);
        model.addAttribute("carId", car.getId());
        model.addAttribute("userEmail", authentication.getName());
        model.addAttribute("bookingDTO", new BookingRequestDTO());

        return "booking/booking-form";
    }

    // Handle booking submission
    @PostMapping("/payment")
    public String submitBooking(@Valid @ModelAttribute("bookingDTO") BookingRequestDTO bookingDTO,
                                BindingResult result,
                                Authentication authentication,
                                Model model) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }

        // Check for validation errors
        if (result.hasErrors()) {
            // Re-fetch car details for the form
            Car car = carService.getCarById(bookingDTO.getCarId());
            model.addAttribute("car", car);
            model.addAttribute("carId", car.getId());
            return "booking/booking-form";
        }

        User user = userService.findByEmail(authentication.getName());
        
        // Create booking
        Booking booking = bookingService.createBooking(bookingDTO, user);
        
        // Check if booking creation failed
        if (booking == null) {
            model.addAttribute("errorMessage", "Car not found or booking failed");
            return "redirect:/cars";
        }

        // Booking succeeded, go to payment page
        model.addAttribute("booking", booking);
        return "booking/payment";
    }

    // Confirm booking
    @PostMapping("/confirm")
    public String confirmBooking(@RequestParam Long bookingId, Model model) {
        // Update booking status to confirmed and mark car unavailable
        bookingService.confirmBooking(bookingId);
        
        // Get booking details for success page
        Booking booking = bookingService.getBookingById(bookingId);
        model.addAttribute("booking", booking);
        
        return "booking/success";
    }

    // Optional: View booking history
    @GetMapping("/history")
    public String bookingHistory(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }

        User user = userService.findByEmail(authentication.getName());
        model.addAttribute("bookings", bookingService.getBookingsByUser(user));
        
        return "booking/history";
    }
}