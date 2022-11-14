package com.appleshopingmall.member;

import com.appleshopingmall.util.SessionUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Date;


@SpringBootTest
class MemberTest {

    @Autowired
    MemberService memberService;

    MockHttpServletRequest request = new MockHttpServletRequest();

    MemberEntity member;

    @BeforeEach
    void beforeEach(){
        member = MemberEntity.builder()
                .memberFirstname("자바")
                .memberAddress("경기도")
                .memberBirthday(new java.sql.Date(new Date().getTime()))
                .memberEmail("java1234@naver.com")
                .memberPwd("1234")
                .memberPhoneNumber("010-1234-1234")
                .build();
    }

    @DisplayName("회원가입_O")
    @Test
    @Order(1)
    void registerTest_O() {
        Assertions.assertThat(memberService.addMember(member)).isEqualTo(1);
    }

    @DisplayName("로그인_O")
    @Test
    @Order(2)
    void login_O(){
        MemberEntity findMember = memberService.findMember(member);
        request.getSession().setAttribute(SessionUtil.MEMBER_ID, findMember.getMemberID());
        Assertions.assertThat(member.getMemberFirstname()).isEqualTo(findMember.getMemberFirstname());
    }

    @DisplayName("중복회원_O")
    @Test
    @Order(2)
    void registerTest_X(){
        org.junit.jupiter.api.Assertions.assertThrows(DuplicateKeyException.class, () -> {
            memberService.addMember(member);
        });
    }

    @DisplayName("회원정보 가져오기")
    @Test
    @Order(3)
    void findMember(){
        MemberEntity findMember = memberService.findMember(member); // 세션아이디를 가져온다

        MemberEntity findMemberId = memberService.findByMemberId(findMember.getMemberID());

        Assertions.assertThat(findMemberId.getMemberFirstname()).isEqualTo(member.getMemberFirstname());
    }

    @DisplayName("회원탈퇴_O")
    @Test
    @Order(4)
    void deleteMember_O() {
        Long memberId = (Long) request.getAttribute(SessionUtil.MEMBER_ID);
        Assertions.assertThat(memberService.deleteMember(memberId, member.getMemberEmail(), member.getMemberPwd())).isEqualTo(1);
    }

    @DisplayName("회원탈퇴_X")
    @Test
    @Order(5)
    void deleteMember_X(){
        Long memberId = (Long) request.getAttribute(SessionUtil.MEMBER_ID);
        Assertions.assertThat(memberService.deleteMember(memberId, member.getMemberEmail(), member.getMemberPwd())).isEqualTo(0);
    }
}