package com.example.car_rent.service;

import com.example.car_rent.entity.User;

public interface UserService {

    User findByEmail(String email);

    User save(User user);
}
