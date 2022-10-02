package com.appleshopingmall.member;

import com.appleshopingmall.sessionUtill.SessionUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입
     * @return
     */
    @GetMapping("register")
    public String getRegister(){
        return "/member/register";
    }

    @PostMapping("register")
    public String postRegister(MemberEntity member){
        log.debug("member -> {}", member);
        memberService.addMember(member);
        return "redirect:/member/login";
    }

    // 세션이 있으면 카트 이동, 없으면 다시 로그인 페이지
    @GetMapping("/login")
    public String login(HttpSession session) {
        return (SessionUtill.getSessionUtill().hasSession(session) ? "redirect:/" : "/member/login");
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberEntity memberEntity, HttpSession session) {
        MemberEntity findMember = memberService.findMember(memberEntity);
        if(findMember != null) SessionUtill.getSessionUtill().addSession(session, findMember);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    /*
    프로젝트 하는중 문제 발생한 곳 mybatis 셋팅이 문제 있음.
    * mybatis 순서
    * 1. datasource 설정이 잘 되있는지 ( O )
    * 2. mybatis 설정 잘 되있는지
    * 3. mybatis-configure 확인 (application-properties) (O)
    * */
//    @GetMapping("test")
    @ResponseBody
    public String test() throws SQLException {
        //mapper 테스트
//        return memberService.getMembers().getMemberFirstname();
        return null;
    }
}
