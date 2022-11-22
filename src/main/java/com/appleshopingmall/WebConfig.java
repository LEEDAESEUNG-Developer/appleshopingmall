package com.appleshopingmall;

import com.appleshopingmall.interceptor.AdminSessionInterceptor;
import com.appleshopingmall.interceptor.ControllerInterceptor;
import com.appleshopingmall.interceptor.MemberSessionInterceptor;
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
                .addPathPatterns("/shop/product/**");*/

        // 회원 검사
        registry.addInterceptor(new MemberSessionInterceptor())
                .order(1)
                .addPathPatterns("/shop/cart", "/shop/order/payment", "/member/**")
                .excludePathPatterns("/member/login", "/member/register");

        // 관리자 검사
        registry.addInterceptor(new AdminSessionInterceptor())
                .order(2)
                .addPathPatterns("/admin/**");
    }
}
