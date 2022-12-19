package com.appleshopingmall.controller.api;

import com.appleshopingmall.error.exception.EmailException;
import com.appleshopingmall.service.MemberService;
import com.appleshopingmall.controller.api.validation.EmailCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;
/*
    // 이메일 중복 체크
    @PostMapping("emailCheck")
    public boolean emailCheck(String email){
        log.debug("email => {}", email);
        return memberService.emailCheck(email);
    }*/
    // 이메일 중복 체크

    @PostMapping("emailCheck")
    public ResponseEntity<?> emailCheck(@Validated EmailCheck emailCheck, BindingResult bindingResult){
        log.debug("email => {}", emailCheck);

        try{
            if(bindingResult.hasErrors()) throw new EmailException();
            if(memberService.emailCheck(emailCheck.getEmail())) {
                return new ResponseEntity<>("true", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("false", HttpStatus.OK);
            }
        } catch (EmailException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
