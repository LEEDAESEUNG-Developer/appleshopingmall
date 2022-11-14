package com.appleshopingmall.member.web;

import com.appleshopingmall.member.MemberEntity;
import com.appleshopingmall.member.MemberService;
import com.appleshopingmall.member.web.form.MemberAddForm;
import com.appleshopingmall.member.web.form.MemberLeaveForm;
import com.appleshopingmall.member.web.form.MemberLoginForm;
import com.appleshopingmall.member.web.form.MemberUpdateForm;
import com.appleshopingmall.util.SessionUtil;
import com.appleshopingmall.util.DateConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
    public String login(@Validated @ModelAttribute("loginForm") MemberLoginForm loginForm, BindingResult bindingResult, HttpSession session) {

        log.info("loginForm => {}", loginForm);

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
        if (!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/member/login";

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        MemberEntity findMember = memberService.findByMemberId(memberId);
        MemberUpdateForm findMemberUpdate = new MemberUpdateForm(findMember.getMemberFirstname(), findMember.getMemberPwd(), findMember.getMemberPhoneNumber(), findMember.getMemberEmail(), findMember.getMemberBirthday(), findMember.getMemberAddress());
        model.addAttribute("form", findMemberUpdate);
        return "/member/member";
    }

    // 회원수정 완료 post
    @PostMapping("/change")
    public String memberChange(HttpSession httpSession, @Validated @ModelAttribute("form") MemberUpdateForm form, BindingResult bindingResult) {
        if (!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/member/login";


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
        if (!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/";

        if(bindingResult.hasErrors()) return "/member/leave";

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        int deleteCnt = memberService.deleteMember(memberId, form.getEmail(), form.getPassword());

        if (deleteCnt == 0){
            bindingResult.addError(new FieldError("memberEntity", "password", "비밀번호가 일치하지 않음."));
            return "member/leave";
        }

        return "redirect:/member/logout";
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
