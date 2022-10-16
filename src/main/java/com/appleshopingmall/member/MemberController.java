package com.appleshopingmall.member;

import com.appleshopingmall.sessionUtill.SessionUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * @return /member/login 이동
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

    /**
     * 로그인 페이지
     * @param session session
     * @return 세션 없을시 /member/login, 있을시 메인 이동
     */
    @GetMapping("/login")
    public String getLogin(HttpSession session) {
        return (SessionUtill.getSessionUtill().hasSession(session) ? "redirect:/" : "/member/login");
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute MemberEntity memberEntity, HttpSession session) {
        MemberEntity findMember = memberService.findMember(memberEntity);
        if(findMember != null) SessionUtill.getSessionUtill().addSession(session, findMember);
        return "redirect:/";
    }

    // 회원보기
    @GetMapping
    public String getMember(HttpSession httpSession, Model model) {
        String url = "redirect:/member/login";
        Long memberId = SessionUtill.getSessionUtill().getMemberID(httpSession);
        if(SessionUtill.getSessionUtill().hasSession(httpSession)){
            url = "member/memberView";
            MemberEntity findMember = memberService.findByMemberId(memberId);
            model.addAttribute("memberEntity", findMember);
        }
            return url;
    }

    // 회원수정 완료 post
    @PostMapping("/change")
    public String postChange(HttpSession httpSession, @ModelAttribute MemberEntity member) {
        String url = "redirect:/member/login";
        if(SessionUtill.getSessionUtill().hasSession(httpSession)){
            url = "redirect:/";
            Long memberID = SessionUtill.getSessionUtill().getMemberID(httpSession);
            member.setMemberID(memberID);
            memberService.updateMember(member);
        }
        return url;
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/resign")
    public String getResign(HttpSession httpSession, Model model) {
        String url = "redirect:/";
        if (SessionUtill.getSessionUtill().hasSession(httpSession)) {
            Long memberID = SessionUtill.getSessionUtill().getMemberID(httpSession);
            MemberEntity findMember = memberService.findByMemberId(memberID);
            model.addAttribute("memberEntity", findMember);
            url = "member/memberResign";
        }
        return url;
    }

    @PostMapping("/resign")
    public String postResign(HttpSession httpSession, @ModelAttribute MemberEntity member){
        String url = "redirect:/";
        if (SessionUtill.getSessionUtill().hasSession(httpSession)) {
            Long memberID = SessionUtill.getSessionUtill().getMemberID(httpSession);
            member.setMemberID(memberID);
            memberService.deleteMember(member);
            url = "redirect:/member/logout";
        }
        return url;
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
