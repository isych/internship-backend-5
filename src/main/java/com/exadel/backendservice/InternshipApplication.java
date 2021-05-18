package com.exadel.backendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternshipApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(InternshipApplication.class);
        app.setBanner((environment, sourceClass, out) -> {});
        app.run(args);
    }
}
