package com.example.car_rent.service.imp;

import com.example.car_rent.entity.Car;
import com.example.car_rent.repository.CarRepository;
import com.example.car_rent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    // 🔍 Search (name + brand)
    @Override
    public List<Car> searchCars(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAvailableCars();
        }
        return carRepository.search(keyword);
    }

    // 🎯 Filter by brand
    @Override
    public List<Car> filterByBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            return getAvailableCars();
        }
        return carRepository.findByBrandIgnoreCase(brand);
    }

    // 🎯 Filter by seats
    @Override
    public List<Car> filterBySeats(Integer seats) {
        if (seats == null) {
            return getAvailableCars();
        }
        return carRepository.findBySeats(seats);
    }
}
