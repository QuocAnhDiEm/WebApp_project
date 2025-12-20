package com.example.car_rent.repository;

import com.example.car_rent.entity.Booking;
import com.example.car_rent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);
}
