package com.example.layerd_architecture.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.example.layerd_architecture.dao",  "com.example.layerd_architecture.service"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}