package com.sparta.bolgpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BolgPostApplication {
    public static void main(String[] args) {
        SpringApplication.run(BolgPostApplication.class, args);
    }
}