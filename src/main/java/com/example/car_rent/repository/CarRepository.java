package com.example.car_rent.repository;

import com.example.car_rent.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    // Search by name or brand
    @Query("""
        SELECT c FROM Car c
        WHERE lower(c.name) LIKE lower(concat('%', :keyword, '%'))
           OR lower(c.brand) LIKE lower(concat('%', :keyword, '%'))
    """)
    List<Car> search(@Param("keyword") String keyword);

    // Filter by brand
    List<Car> findByBrandIgnoreCase(String brand);

    // Filter by seats
    List<Car> findBySeats(int seats);

    // Filter by type
    List<Car> findByTypeIgnoreCase(String type);

    // Filter by availability
    List<Car> findByAvailableTrue();
}
