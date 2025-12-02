package com.example.Invoice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface HandlerInterceptor {
    boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler);
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView);
    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex);
}
