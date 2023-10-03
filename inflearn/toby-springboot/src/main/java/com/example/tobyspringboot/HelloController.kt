package com.example.tobyspringboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val helloService: SimpleHelloService
) {
    @GetMapping("/hello")
    fun hello(name: String): String {
        return helloService.sayHello(name)
    }
}
