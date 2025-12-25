package com.example.car_rent.service;

import com.example.car_rent.entity.Car;
import java.util.List;

public interface CarService {

    List<Car> getAvailableCars();

    Car getCarById(Long id);

    // 🔍 Search by name or brand
    List<Car> searchCars(String keyword);

    // 🎯 Filters
    List<Car> filterByBrand(String brand);

    List<Car> filterBySeats(Integer seats);
}
