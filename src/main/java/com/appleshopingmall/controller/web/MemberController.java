package com.appleshopingmall.controller.web;

import com.appleshopingmall.controller.web.adminForm.PasswordReset;
import com.appleshopingmall.controller.web.memberForm.*;
import com.appleshopingmall.entity.MemberEntity;
import com.appleshopingmall.service.MemberService;
import com.appleshopingmall.util.SessionUtil;
import com.appleshopingmall.util.DateConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;

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
    public String registerForm(Model model){
        model.addAttribute("form", new MemberAddForm());
        return "/member/register";
    }

    @PostMapping("register")
    public String register(@Validated @ModelAttribute("form") MemberAddForm form, BindingResult bindingResult){
        log.debug("form -> {}", form);

        // 입력 오류
        if (bindingResult.hasErrors()) {
            log.info("오류");
            return "/member/register";
        }

        MemberEntity member = MemberEntity.builder()
                .memberFirstname(form.getName())
                .memberPwd(form.getPassword())
                .memberPhoneNumber(form.getPhoneNumber())
                .memberEmail(form.getEmail())
                .memberBirthday(DateConverter.utilDateToSql(form.getBirthday()))
                .memberAddress(form.getAddress())
                .build();


        try {
            memberService.addMember(member);
        } catch (DuplicateKeyException e) {
            bindingResult.addError(new ObjectError("form", "중복회원"));
            return "/member/register";
        }

        return "redirect:/member/login";
    }

    /**
     * 로그인 페이지
     * @param session session
     * @return 세션 없을시 /member/login, 있을시 메인 이동
     */
    @GetMapping("/login")
    public String loginForm(HttpSession session, Model model, HttpServletRequest request) {

        // 세션이 없는 경우 /member/login 이동
        if (!SessionUtil.getSessionUtil().hasSession(session)){

            MemberLoginForm memberLoginForm = new MemberLoginForm();

            // 쿠키 저장된 아이디 가져오기
            Cookie id = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("id")).findFirst().orElse(new Cookie("id", ""));

            memberLoginForm.setEmail(id.getValue());

            model.addAttribute("loginForm", memberLoginForm);
            return "/member/login";
        }

        // 세션이 있는경우 /으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") MemberLoginForm loginForm, BindingResult bindingResult, HttpSession session, HttpServletResponse response) {

        log.info("loginForm => {}", loginForm);

        if(loginForm.isIdSaveChk()) response.addCookie(new Cookie("id", loginForm.getEmail()));

        // 이메일, 비밀번호 문제가 있을시
        if(bindingResult.hasErrors()){
            log.info("이메일, 비밀번호 입력 오류 => {}", bindingResult);
            return "/member/login";
        }

        MemberEntity member = MemberEntity.builder().memberEmail(loginForm.getEmail()).memberPwd(loginForm.getPassword()).build();

        MemberEntity findMember = memberService.findMember(member);

        // 로그인 실패시
        if(findMember == null){
            bindingResult.addError(new ObjectError("loginForm", "아이디 또는 비밀번호를 잘못 입력하였습니다."));
            log.info("target => {} ", bindingResult.getTarget());
            return "/member/login";
        }

        SessionUtil.getSessionUtil().addSession(session, findMember);
        return "redirect:/";
    }

    // 회원보기
    @GetMapping
    public String memberForm(HttpSession httpSession, Model model) {
        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        MemberEntity findMember = memberService.findByMemberId(memberId);
        MemberUpdateForm findMemberUpdate = new MemberUpdateForm(findMember.getMemberFirstname(), findMember.getMemberPwd(), findMember.getMemberPhoneNumber(), findMember.getMemberEmail(), findMember.getMemberBirthday(), findMember.getMemberAddress());
        model.addAttribute("form", findMemberUpdate);
        return "/member/member";
    }

    // 회원수정 완료 post
    @PostMapping("/change")
    public String memberChange(HttpSession httpSession, @Validated @ModelAttribute("form") MemberUpdateForm form, BindingResult bindingResult) {


        if(bindingResult.hasErrors()) return "/member/member";

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);

        MemberEntity memberUpdate = MemberEntity.builder().memberFirstname(form.getName()).memberPwd(form.getPassword()).memberPhoneNumber(form.getPhoneNumber()).
                memberEmail(form.getEmail()).memberAddress(form.getAddress()).build();

        memberService.updateMember(memberId, memberUpdate);
        return "redirect:/member";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/leave")
    public String leaveForm(HttpSession httpSession, Model model) {
        if(!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/";

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        MemberEntity findMember = memberService.findByMemberId(memberId);

        MemberLeaveForm form = new MemberLeaveForm(findMember.getMemberFirstname(), findMember.getMemberPwd(), findMember.getMemberPhoneNumber(), findMember.getMemberEmail(), findMember.getMemberBirthday());

        model.addAttribute("memberEntity", form);
        return "/member/leave";
    }

    @PostMapping("/leave")
    public String leave(HttpSession httpSession, @Validated @ModelAttribute("memberEntity") MemberLeaveForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "/member/leave";

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        int deleteCnt = memberService.deleteMember(memberId, form.getEmail(), form.getPassword());

        if (deleteCnt == 0){
            bindingResult.addError(new FieldError("memberEntity", "password", "비밀번호가 일치하지 않음."));
            return "member/leave";
        }

        return "redirect:/member/logout";
    }

    @GetMapping("password-reset")
    public String passwordResetConfirmForm(Model model){
        model.addAttribute("form", new PasswordReset());
        return "member/password-reset-email";
    }

    @PostMapping("password-reset")
    public String passwordResetConfirm(@Validated @ModelAttribute("form") PasswordReset passwordReset, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {

        log.debug("email => {}", passwordReset.getEmail());

        // 입력한 값에 오류가 있을 경우
        if(bindingResult.hasErrors()){
            return "member/password-reset-email";
        }

        // 입력 받은 값을 DB 조회시 없는 회원 일 경우
        if(!memberService.findByEmail(passwordReset.getEmail())){
            bindingResult.addError(new ObjectError("form", "입력하신 이메일은 없는 이메일 입니다. 회원가입 해주세요"));
            return "member/password-reset-email";
        }

        String email = passwordReset.getEmail();
        session.setAttribute("email", email);
        session.setMaxInactiveInterval(180); // 3분 동안 세션에 저장

        redirectAttributes.addAttribute("email", email);

        return "redirect:/member/password-reset/{email}";
    }

    @GetMapping("password-reset/{email}")
    public String passwordResetForm(@PathVariable String email, @SessionAttribute(value = "email") String sessionEmail, Model model){
        log.debug("email => {}", email);
        log.debug("sessionEmail => {}", sessionEmail);

        model.addAttribute("form", new MemberPasswordResetForm());

        return "/member/password_reset";
    }

    @PostMapping("password-reset/{email}")
    public String passwordReset(@PathVariable String email, @SessionAttribute(value = "email") String sessionEmail, HttpSession session, @ModelAttribute("form") MemberPasswordResetForm form){
        log.debug("email => {}", email);
        memberService.updateMemberPassword(sessionEmail, form.getPassword());
        session.invalidate();
        return "redirect:/member/login";
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
