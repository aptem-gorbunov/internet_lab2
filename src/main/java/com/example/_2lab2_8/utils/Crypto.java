package com.example._2lab2_8.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class Crypto {

    private static final int strength = 10;

    public static String hashPassword(String plainPassword) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());

        return bCryptPasswordEncoder.encode(plainPassword);
    }

    public static Boolean mathesPasswords(String hashedPassword, String plainPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.matches(plainPassword, hashedPassword);
    }
}
