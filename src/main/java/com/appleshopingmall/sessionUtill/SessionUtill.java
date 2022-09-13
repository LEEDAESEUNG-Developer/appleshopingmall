package com.appleshopingmall.sessionUtill;

import com.appleshopingmall.member.MemberEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtill {

    private static SessionUtill sessionUtill = new SessionUtill();

    private SessionUtill(){}

    public static SessionUtill getSessionUtill() {
        return sessionUtill;
    }

    // 로그인 성공시 세션 생성
    public void addSession(HttpSession httpSession, MemberEntity member){
        httpSession.setAttribute("memberID", member.getMemberID());
    }

    // 현재 접속자가 세션이 있는 사람인지?
    public boolean isSession(HttpSession httpSession) {
        return (httpSession.getAttribute("memberID") != null);
    }

    // memberID 세션을 가져옴
    public Long getMemberID(HttpSession httpSession) {
        return (Long)httpSession.getAttribute("memberID");
    }

}
