package com.example.car_rent.service.imp;

import com.example.car_rent.dto.BookingRequestDTO;
import com.example.car_rent.entity.Booking;
import com.example.car_rent.entity.Car;
import com.example.car_rent.entity.User;
import com.example.car_rent.repository.BookingRepository;
import com.example.car_rent.repository.CarRepository;
import com.example.car_rent.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Booking createBooking(BookingRequestDTO dto, User user) {
        Car car = carRepository.findById(dto.getCarId()).orElse(null);
        if (car == null) {
            return null;
        }

        // Calculate days and total price
        long days = ChronoUnit.DAYS.between(
                dto.getPickUpDate(),
                dto.getDropOffDate()
        );

        double totalPrice = days * car.getPricePerDay();

        // Create new booking with all fields
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        
        // Set all customer information from DTO
        booking.setFirstName(dto.getFirstName());
        booking.setLastName(dto.getLastName());
        booking.setEmail(dto.getEmail());
        booking.setPhone(dto.getPhone());
        booking.setPickUpLocation(dto.getPickUpLocation());
        booking.setDropOffLocation(dto.getDropOffLocation());
        booking.setNotes(dto.getNotes());
        
        // Set dates and pricing
        booking.setPickUpDate(dto.getPickUpDate());
        booking.setDropOffDate(dto.getDropOffDate());
        booking.setTotalPrice(totalPrice);
        booking.setStatus("PENDING");

        // Don't mark car unavailable yet - only when booking is confirmed
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public void confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            // Update booking status
            booking.setStatus("CONFIRMED");
            
            // Now mark the car as unavailable
            Car car = booking.getCar();
            car.setAvailable(false);
            carRepository.save(car);
            
            bookingRepository.save(booking);
        }
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }
}