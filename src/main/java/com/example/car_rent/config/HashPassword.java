package com.example.car_rent.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashPassword {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Hash a raw password using BCrypt
     */
    public static String hash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * Verify raw password against hashed password
     */
    public static boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }

    // Quick test
    public static void main(String[] args) {
        String password = "test123";
        String hash = hash(password);

        System.out.println("Raw password: " + password);
        System.out.println("BCrypt hash : " + hash);
        System.out.println("Match test  : " + matches(password, hash));
    }
}
