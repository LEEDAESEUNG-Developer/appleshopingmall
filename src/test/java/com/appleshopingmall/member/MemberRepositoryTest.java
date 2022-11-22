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

}