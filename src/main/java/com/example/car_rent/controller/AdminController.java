package com.example.car_rent.controller;

import com.example.car_rent.entity.Car;
import com.example.car_rent.repository.BookingRepository;
import com.example.car_rent.repository.CarRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
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
        model.addAttribute("activeBookings", bookingRepository.countByStatus("ACTIVE"));
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {

        bookingRepository.findById(id).ifPresent(booking -> {
            Car car = booking.getCar();
            car.setAvailable(true);
            carRepository.save(car);
            bookingRepository.delete(booking);
        });

        redirectAttributes.addFlashAttribute("success", "Booking deleted successfully");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/car/add")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "admin/add-car";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute Car car) {
        MultipartFile imageFile = car.getImageFile();

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                car.setImage(filename);

                // ✅ Save to static/images/cars
                Path uploadPath = Paths.get("src/main/resources/static/images/cars");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(filename);
                imageFile.transferTo(filePath.toFile());


            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        carRepository.save(car);
        return "redirect:/admin/dashboard";
    }
}
