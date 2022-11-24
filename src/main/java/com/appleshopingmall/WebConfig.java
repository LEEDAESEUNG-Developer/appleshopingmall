package com.appleshopingmall;

import com.appleshopingmall.interceptor.AdminSessionInterceptor;
import com.appleshopingmall.interceptor.ControllerInterceptor;
import com.appleshopingmall.interceptor.MemberSessionInterceptor;
import com.appleshopingmall.interceptor.PasswordResetInterceptor;
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

        // 회원 비밀번호 찾기 세션 검사
        registry.addInterceptor(new PasswordResetInterceptor())
                .order(1)
                .addPathPatterns("/member/password-reset/**")
                .excludePathPatterns("/member/password-reset");

        // 회원 세션 검사
        registry.addInterceptor(new MemberSessionInterceptor())
                .order(2)
                .addPathPatterns("/shop/cart", "/shop/order/payment", "/member/**")
                .excludePathPatterns("/member/login", "/member/register", "/member/password-reset", "/member/password-reset/**", "/member/logout");

        // 관리자 세션 검사
        registry.addInterceptor(new AdminSessionInterceptor())
                .order(3)
                .addPathPatterns("/admin/**");


    }
}
