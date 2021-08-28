package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    @Autowired
    Engine v8;

    public Car() {
        System.out.println("Car 생성자");
    }

    public void setEngine(Engine e) {
        this.v8 = e;
    }

    public void run() {
        System.out.println("엔진을 이용하여 달립니다.");
        v8.exec();
    }
}