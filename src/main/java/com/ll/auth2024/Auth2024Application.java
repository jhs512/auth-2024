package com.ll.auth2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Auth2024Application {

    public static void main(String[] args) {
        SpringApplication.run(Auth2024Application.class, args);
    }

}
