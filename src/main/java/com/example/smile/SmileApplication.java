package com.example.smile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SmileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmileApplication.class, args);
    }
}
