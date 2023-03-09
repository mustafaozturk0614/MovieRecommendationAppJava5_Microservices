package com.bilgeadam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
    Auth sınıfı içerisinde
    => username
    =>email
    =>password
    =>activationCode
    =>Role=>enum
    =>Status=>enum=>Deleted ,Pending,Active
 */
@SpringBootApplication
@EnableFeignClients
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class);
    }
}