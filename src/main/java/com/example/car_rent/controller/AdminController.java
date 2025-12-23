package com.example.car_rent.controller;

import com.example.car_rent.entity.Car;
import com.example.car_rent.repository.BookingRepository;
import com.example.car_rent.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalCars", carRepository.count());
        model.addAttribute("totalBookings", bookingRepository.count());
        model.addAttribute("activeBookings",
                bookingRepository.countByStatus("ACTIVE"));

        // 🔥 THIS IS THE IMPORTANT PART
        model.addAttribute("bookings", bookingRepository.findAll());

        return "admin/dashboard";
    }

    @GetMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {

        bookingRepository.findById(id).ifPresent(booking -> {

            // ✅ make car available again
            Car car = booking.getCar();
            car.setAvailable(true);
            carRepository.save(car);

            // ✅ delete booking
            bookingRepository.delete(booking);
        });

        redirectAttributes.addFlashAttribute("success",
                "Booking deleted successfully");

        return "redirect:/admin/dashboard";
    }
}
