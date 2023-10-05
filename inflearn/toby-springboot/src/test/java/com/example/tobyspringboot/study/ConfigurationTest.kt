package com.example.tobyspringboot.study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ConfigurationTest {
    @Test
    fun configuration() {
        val ac = AnnotationConfigApplicationContext()
        ac.register(MyConfig::class.java)
        ac.refresh()

        val bean1 = ac.getBean(Bean1::class.java)
        val bean2 = ac.getBean(Bean2::class.java)

        Assertions.assertThat(bean1.common).isSameAs(bean2.common)
    }

    @Configuration
    class MyConfig {
        @Bean
        fun common(): Common {
            return Common()
        }

        @Bean
        fun bean1(): Bean1 {
            return Bean1(common())
        }

        @Bean
        fun bean2(): Bean2 {
            return Bean2(common())
        }
    }

    class Bean1(val common: Common)

    class Bean2(val common: Common)

    class Common
}
