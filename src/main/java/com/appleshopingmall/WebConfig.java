package com.appleshopingmall;

import com.appleshopingmall.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(new ControllerInterceptor())
                .order(1)
                .addPathPatterns("/member/login");*/
    }
}
