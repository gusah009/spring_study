package com.example.config.autoconfig

import com.example.config.ConditionalMyOnClass
import com.example.config.MyAutoConfiguration
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils

@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
class JettyWebServerConfig {
    @Bean("jettyServletWebServerFactory")
    fun servletWebServerFactory(): ServletWebServerFactory {
        return JettyServletWebServerFactory()
    }
}
