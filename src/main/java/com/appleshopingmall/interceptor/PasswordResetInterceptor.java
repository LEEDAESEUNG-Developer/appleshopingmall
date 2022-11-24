package com.appleshopingmall.interceptor;

import com.appleshopingmall.error.exception.EmailSessionExcepiton;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PasswordResetInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");
        if(email == null) throw new EmailSessionExcepiton();

        return true;
    }
}
