package com.appleshopingmall.util;

import com.appleshopingmall.entity.MemberEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtil {

    private static final SessionUtil SESSION_UTIL = new SessionUtil();
    public static final String MEMBER_ID = "memberID";
    public static final String MEMBER_POSITION = "position";

    private static final String DB_POSITION = "ADMIN";

    private SessionUtil(){}

    public static SessionUtil getSessionUtil() {
        return SESSION_UTIL;
    }

    // 로그인 성공시 세션 생성
    public void addSession(HttpSession httpSession, MemberEntity member){
        if(member.getPosition().equals("ADMIN")) httpSession.setAttribute(MEMBER_POSITION, "ADMIN");

        httpSession.setAttribute("memberName", member.getMemberFirstname());
        httpSession.setAttribute(MEMBER_ID, member.getMemberID());
    }

    /**
     * 현재 접속자 세션 검사
     * @param httpSession HttpSession
     * @return 있으면 true, 없으면 false
     */
    public boolean hasSession(HttpSession httpSession) {
        return (getMemberId(httpSession) != null);
    }

    /**
     * 현재 접속자 관리자 검사
     * @param httpSession HttpSession
     * @return 관리자면 false, 아니면 true
     */
    public boolean hasAdminSession(HttpSession httpSession){
        return (httpSession.getAttribute(MEMBER_POSITION) == null);
    }

    // memberID 세션을 가져옴
    public Long getMemberId(HttpSession httpSession) {
        return (Long)httpSession.getAttribute(MEMBER_ID);
    }

}
