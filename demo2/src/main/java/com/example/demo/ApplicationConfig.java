package com.example.demo;

import org.springframework.context.annotation.*;

@Configuration
public class ApplicationConfig {
    @Bean
    public Car car(Engine e) {
        Car c = new Car();
        c.setEngine(e);
        return c;
    }

    @Bean
    public Engine engine() {
        return new Engine();
    }
}
