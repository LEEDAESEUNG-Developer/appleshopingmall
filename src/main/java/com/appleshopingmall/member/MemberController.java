package com.appleshopingmall.member;

import com.appleshopingmall.member.web.form.MemberLoginForm;
import com.appleshopingmall.member.web.form.MemberUpdateForm;
import com.appleshopingmall.sessionUtill.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String loginForm(HttpSession session, Model model) {

        // 세션이 없는 경우 /member/login 이동
        if (!SessionUtil.getSessionUtil().hasSession(session)){
            model.addAttribute("loginForm", new MemberLoginForm());
            return "/member/login";
        }

        // 세션이 있는경우 /으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute MemberLoginForm memberLoginForm, BindingResult bindingResult, HttpSession session) {

        // 이메일, 비밀번호 문제가 있을시
        if(bindingResult.hasErrors()){
            log.info("오류 발생 => {}", bindingResult);
            return "/member/login";
        }

        MemberEntity member = MemberEntity.builder().memberEmail(memberLoginForm.getEmail()).memberPwd(memberLoginForm.getPassword()).build();

        MemberEntity findMember = memberService.findMember(member);

        // 로그인 실패시
        if(findMember == null){
            bindingResult.addError(new ObjectError("loginForm", "아이디 또는 비밀번호를 잘못 입력하였습니다."));
            return "/member/login";
        }

        SessionUtil.getSessionUtil().addSession(session, findMember);
        return "redirect:/";
    }

    // 회원보기
    @GetMapping
    public String memberForm(HttpSession httpSession, Model model) {
        if (!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/member/login";

        Long memberId = SessionUtil.getSessionUtil().getMemberID(httpSession);
        MemberEntity findMember = memberService.findByMemberId(memberId);
        MemberUpdateForm findMemberUpdate = new MemberUpdateForm(findMember.getMemberFirstname(), findMember.getMemberPwd(), findMember.getMemberPhoneNumber(), findMember.getMemberEmail(), findMember.getMemberBirthday(), findMember.getMemberAddress());
        model.addAttribute("form", findMemberUpdate);
        return "/member/member";
    }

    // 회원수정 완료 post
    @PostMapping("/change")
    public String memberChange(HttpSession httpSession, @Validated @ModelAttribute("form") MemberUpdateForm form,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/member/login";


        if(bindingResult.hasErrors()){
            log.info("오류 퉤 =>{}", bindingResult);
            return "/member/member";
        }

        Long memberId = SessionUtil.getSessionUtil().getMemberID(httpSession);

        MemberEntity memberUpdate = MemberEntity.builder().memberFirstname(form.getName()).memberPwd(form.getPassword()).memberPhoneNumber(form.getPhoneNumber()).
                memberEmail(form.getEmail()).memberAddress(form.getAddress()).build();

        memberService.updateMember(memberId, memberUpdate);
        return "redirect:/member";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/resign")
    public String getResign(HttpSession httpSession, Model model) {
        String url = "redirect:/";
        if (SessionUtil.getSessionUtil().hasSession(httpSession)) {
            Long memberID = SessionUtil.getSessionUtil().getMemberID(httpSession);
            MemberEntity findMember = memberService.findByMemberId(memberID);
            model.addAttribute("memberEntity", findMember);
            url = "/member/memberResign";
        }
        return url;
    }

    @PostMapping("/resign")
    public String postResign(HttpSession httpSession, @ModelAttribute MemberEntity member){
        String url = "redirect:/";
        if (SessionUtil.getSessionUtil().hasSession(httpSession)) {
            Long memberID = SessionUtil.getSessionUtil().getMemberID(httpSession);
            MemberEntity memberEntity = MemberEntity.builder().memberID(memberID).build();
            memberService.deleteMember(memberEntity);
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
        //테스트
//        return memberService.getMembers().getMemberFirstname();
        return null;
    }
}
