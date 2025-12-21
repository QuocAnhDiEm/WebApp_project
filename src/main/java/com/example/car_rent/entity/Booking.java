package com.example.car_rent.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false)
    private LocalDate pickUpDate;

    @Column(nullable = false)
    private LocalDate dropOffDate;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "pickup_location")
    private String pickUpLocation;

    @Column(name = "dropoff_location")
    private String dropOffLocation;

    @Column(length = 500)
    private String notes;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String status; // PENDING, CONFIRMED, CANCELLED

    // ===== Constructors =====
    public Booking() {}

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public LocalDate getPickUpDate() { return pickUpDate; }
    public void setPickUpDate(LocalDate pickUpDate) { this.pickUpDate = pickUpDate; }

    public LocalDate getDropOffDate() { return dropOffDate; }
    public void setDropOffDate(LocalDate dropOffDate) { this.dropOffDate = dropOffDate; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPickUpLocation() { return pickUpLocation; }
    public void setPickUpLocation(String pickUpLocation) { this.pickUpLocation = pickUpLocation; }

    public String getDropOffLocation() { return dropOffLocation; }
    public void setDropOffLocation(String dropOffLocation) { this.dropOffLocation = dropOffLocation; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getTotalDays() {
        if (pickUpDate != null && dropOffDate != null) {
            return ChronoUnit.DAYS.between(pickUpDate, dropOffDate);
        }
        return null;
    }
}
