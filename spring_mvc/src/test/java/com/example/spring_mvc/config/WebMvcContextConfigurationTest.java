package com.example.spring_mvc.config;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,
        classes = { WebMvcContextConfiguration.class})
public class WebMvcContextConfigurationTest {
}
