package com.example.car_rent.entity;

import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Car parent;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    private String type;

    @Column(nullable = false)
    private int seats;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_per_day", nullable = false)
    private double pricePerDay;

    private String image;

    @Column(nullable = false)
    private boolean available = true;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;

    @Transient
    private MultipartFile imageFile;

    // Constructors
    public Car() {
    }

    public Car(String name, String brand, String type, int seats, String description,
               double pricePerDay, String image, boolean available) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.seats = seats;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.image = image;
        this.available = available;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getParent() {
        return parent;
    }

    public void setParent(Car parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
