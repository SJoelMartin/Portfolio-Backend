package com.portfolio.contactBackend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode("jon");

        System.out.println(encodedPassword);
    }
}