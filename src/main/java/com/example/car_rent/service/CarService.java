package com.example.car_rent.service;

import com.example.car_rent.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getAvailableCars();

    Car getCarById(Long id);
}
