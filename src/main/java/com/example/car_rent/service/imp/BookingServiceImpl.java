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
        if (car == null || !car.isAvailable()) {
            return null;
        }

        long days = ChronoUnit.DAYS.between(
                dto.getPickUpDate(),
                dto.getDropOffDate()
        );

        double totalPrice = days * car.getPricePerDay();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        booking.setPickUpDate(dto.getPickUpDate());
        booking.setDropOffDate(dto.getDropOffDate());
        booking.setTotalPrice(totalPrice);
        booking.setStatus("PENDING");

        // Mark car unavailable
        car.setAvailable(false);
        carRepository.save(car);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }
}
