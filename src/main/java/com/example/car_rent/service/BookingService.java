package com.example.car_rent.service;

import com.example.car_rent.entity.Booking;
import com.example.car_rent.entity.User;
import com.example.car_rent.dto.BookingRequestDTO;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingRequestDTO dto, User user);

    List<Booking> getBookingsByUser(User user);
}
