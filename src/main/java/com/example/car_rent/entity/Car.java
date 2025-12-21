package com.example.car_rent.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // This represents the category/type of the car
    private String type;

    @Column(nullable = false)
    private double pricePerDay;

    private String image;

    @Column(nullable = false)
    private boolean available = true;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;

    // ===== Constructors =====
    public Car() {}

    public Car(String name, String type, double pricePerDay, String image, boolean available) {
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.image = image;
        this.available = available;
    }

    // ===== Getters & Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
