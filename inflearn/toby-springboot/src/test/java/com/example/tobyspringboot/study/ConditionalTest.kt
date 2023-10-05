package com.example.tobyspringboot.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.AnnotatedTypeMetadata

class ConditionalTest {
    @Test
    fun conditional() {
        /*
        val ac = AnnotationConfigApplicationContext()
        ac.register(Config1::class.java)
        ac.refresh()

        val bean = ac.getBean(MyBean::class.java)
        */
        ApplicationContextRunner()
            .withUserConfiguration(Config1::class.java)
            .run {
                assertThat(it).hasSingleBean(MyBean::class.java)
                assertThat(it).hasSingleBean(Config1::class.java)
            }

        /*
        val ac2 = AnnotationConfigApplicationContext()
        ac2.register(Config2::class.java)
        ac2.refresh()

        val bean2 = ac2.getBean(MyBean::class.java)
        */
        ApplicationContextRunner()
            .withUserConfiguration(Config2::class.java)
            .run {
                assertThat(it).doesNotHaveBean(MyBean::class.java)
                assertThat(it).doesNotHaveBean(Config2::class.java)
            }

        ApplicationContextRunner()
            .withUserConfiguration(Config3::class.java)
            .run {
                assertThat(it).hasSingleBean(MyBean::class.java)
                assertThat(it).hasSingleBean(Config3::class.java)
            }
    }

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    @Conditional(BooleanCondition::class)
    annotation class BooleanConditional(val value: Boolean)

    @Configuration
    @Conditional(TrueCondition::class)
    class Config1 {
        @Bean
        fun myBean(): MyBean {
            return MyBean()
        }
    }

    @Configuration
    @Conditional(FalseCondition::class)
    class Config2 {
        @Bean
        fun myBean(): MyBean {
            return MyBean()
        }
    }

    @Configuration
    @BooleanConditional(value = true)
    class Config3 {
        @Bean
        fun myBean(): MyBean {
            return MyBean()
        }
    }

    class MyBean

    class TrueCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return true
        }
    }

    class FalseCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return false
        }
    }

    class BooleanCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            val annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional::class.java.name)
            return annotationAttributes!!["value"] as Boolean
        }
    }
}
