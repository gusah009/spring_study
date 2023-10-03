package com.example.tobyspringboot

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseBody

@Component
class SimpleHelloService {
    @ResponseBody
    fun sayHello(name: String): String {
        return "Hello $name"
    }
}
