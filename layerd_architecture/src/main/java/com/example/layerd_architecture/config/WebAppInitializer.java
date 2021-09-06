package com.example.layerd_architecture.config;


import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // Spring 기본 설정파일 클래스를 지정합니다.
// 여러분은 ApplicationConfig.class를 작성해줘야 합니다.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class};
    }

    // Spring MVC 설정 파일 클래스를 지정합니다.
    // 여러분은 MvcConfig.class를 작성해줘야 합니다.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfig.class};
    }

    /*
    DispatcherServlet이 동작할 맵핑정보를 설정합니다.
"/"를 설정한다는 것은 모든 요청을 DispatcherServlet이 처리한다는 것을 의미합니다.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /*
    필터를 설정합니다. 여기에서는 인코딩 필터를 설정하고 있습니다.
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[]{encodingFilter};
    }
}