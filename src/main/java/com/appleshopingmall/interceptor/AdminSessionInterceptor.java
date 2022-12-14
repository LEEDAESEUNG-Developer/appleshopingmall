package com.appleshopingmall.interceptor;

import com.appleshopingmall.error.exception.AdminSessionException;
import com.appleshopingmall.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminSessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session = request.getSession();

        if(SessionUtil.getSessionUtil().hasAdminSession(session)){
            throw new AdminSessionException();
        }

        return true;
    }
}