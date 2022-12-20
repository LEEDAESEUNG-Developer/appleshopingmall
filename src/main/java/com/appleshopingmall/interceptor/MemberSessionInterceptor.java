package com.appleshopingmall.interceptor;

import com.appleshopingmall.error.exception.MemberSessionException;
import com.appleshopingmall.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class MemberSessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();

        if(!SessionUtil.getSessionUtil().hasSession(session)){
            throw new MemberSessionException();
        }

        return true;
    }
}
