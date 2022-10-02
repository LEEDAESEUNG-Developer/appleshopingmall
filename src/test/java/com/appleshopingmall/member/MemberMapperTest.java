package com.appleshopingmall.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberService memberService;

    MemberEntity member = new MemberEntity();

    @BeforeEach
    void beforeEach(){
        member.setMemberFirstname("자바");
        member.setMemberAddress("경기도");
        member.setMemberBirthday(new Timestamp(new Date().getTime()));
        member.setMemberEmail("java1234@naver.com");
        member.setMemberPwd("1234");
        member.setMemberPhoneNumber("010-1234-1234");
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

        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @DisplayName("중복회원_O")
    @Test
    @Order(2)
    void registerTest_X(){
        org.junit.jupiter.api.Assertions.assertThrows(DuplicateKeyException.class, () -> {
            memberService.addMember(member);
        });
    }

    @DisplayName("회원탈퇴_O")
    @Test
    @Order(3)
    void deleteMember_O(){
        Assertions.assertThat(memberService.deleteMember(member)).isEqualTo(1);
    }

    @DisplayName("회원탈퇴_X")
    @Test
    @Order(4)
    void deleteMember_X(){
        Assertions.assertThat(memberService.deleteMember(member)).isEqualTo(0);
    }
}