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
}
