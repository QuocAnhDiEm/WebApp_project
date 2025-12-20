package com.example.car_rent.repository;

import com.example.car_rent.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByAvailableTrue();
}
