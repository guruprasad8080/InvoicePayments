package com.example.Invoice.configuration;

import com.example.Invoice.interceptor.MainInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor((HandlerInterceptor) new MainInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/public/**");

    }
}
