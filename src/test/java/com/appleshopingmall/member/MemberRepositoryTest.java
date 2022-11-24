package com.appleshopingmall.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 아이디중복검사_O(){
        // given
        String email = "aaaaaaaaa";
        // then
        int result = memberRepository.emailCheck(email);
        //when
        assertThat(result).isEqualTo(0);
    }
    @Test
    void 아이디중복검사_X(){

        // given
        String email = "test";
        // then
        int result = memberRepository.emailCheck(email);
        //when
        assertThat(result).isEqualTo(1);
    }

    @Test
    void 이메일존재_O(){
        //given
        String email = "test";

        //then
        int resultEmail = memberRepository.findByEmail(email);

        //when
        assertThat(resultEmail).isEqualTo(1);
    }

    @Test
    void 이메일존재_X(){
        //given
        String email = "akjsdflajflaknxlc,wairup";

        //then
        int resultEmail = memberRepository.findByEmail(email);

        //when
        assertThat(resultEmail).isEqualTo(0);
    }

    @Test
    void 회원비밀번호변경_O(){
        //given
        String email = "test";
        String password = "1234";

        //then

        int updateMemberPasswordResult = memberRepository.updateMemberPassword(email, password);

        //when
        assertThat(updateMemberPasswordResult).isEqualTo(1);

        // 로그인

        //then
        MemberEntity findMemberResult = memberRepository.findMember(MemberEntity.builder().memberEmail(email).memberPwd(password).build());

        // when
        assertThat(findMemberResult.getMemberEmail()).isEqualTo(email);
        assertThat(findMemberResult.getMemberPwd()).isEqualTo(password);
    }

}