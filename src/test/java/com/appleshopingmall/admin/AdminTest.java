package com.appleshopingmall.admin;

import com.appleshopingmall.member.MemberEntity;
import com.appleshopingmall.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTest {

    @Autowired
    private MemberService memberService;

    @DisplayName("관리자 확인")
    @Test
    void isAdmin(){
        MemberEntity member = MemberEntity.builder()
                .memberEmail("admin")
                .memberPwd("admin").build();

        MemberEntity findMember = memberService.findMember(member);
        String position = findMember.getPosition();

        Assertions.assertThat(position).isEqualTo("ADMIN");
    }


}
