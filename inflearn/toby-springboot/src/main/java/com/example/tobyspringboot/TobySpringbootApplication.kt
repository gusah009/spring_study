package com.example.tobyspringboot

import com.example.config.MySpringBootApplication

@MySpringBootApplication
class TobySpringbootApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MySpringApplication.run(TobySpringbootApplication::class.java, args)
        }
    }
}
