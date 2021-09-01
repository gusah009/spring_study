package com.example.layerd_architecture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.example.layerd_architecture.controller" },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        })
public class WebMvcContextConfiguration implements WebMvcConfigurer  {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);

    }

    // default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}