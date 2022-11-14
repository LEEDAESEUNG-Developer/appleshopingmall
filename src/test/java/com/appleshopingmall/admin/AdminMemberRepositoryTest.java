package com.appleshopingmall.admin;

import com.appleshopingmall.admin.repository.AdminMemberRepository;
import com.appleshopingmall.member.MemberEntity;
import com.appleshopingmall.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AdminMemberRepositoryTest {

    @Autowired
    AdminMemberRepository adminMemberRepository;

    @Autowired
    MemberService memberService;

    @DisplayName("모든 회원 조회")
    @Test
    void findMembersTest() {
        List<MemberEntity> findMembers = adminMemberRepository.findMembers();

        findMembers.forEach(member -> System.out.println("member = " + member));

        assertThat(findMembers).isNotEmpty();
    }

    @DisplayName("회원 한명 조회")
    @Test
    void findMemberTest() {
        //given
        Long findMemberId = 46L; // 관리자 아이디

        // when
        MemberEntity member = adminMemberRepository.findMember(findMemberId);
        System.out.println("member = " + member);

        // then
        assertThat(member).isNotNull();
    }

    @DisplayName("회원 삭제")
    @Test
    void deleteMember(){
        // given -> 주어졌을때
        MemberEntity member = MemberEntity.builder()
                .memberFirstname("삭제될계정")
                .memberAddress("없음")
                .memberBirthday(new java.sql.Date(new Date().getTime()))
                .memberEmail("delete@naver.com")
                .memberPwd("1234")
                .memberPhoneNumber("010-1234-1234")
                .build();

        // when -> 처리 했을때
        memberService.addMember(member);
        MemberEntity findMember = memberService.findMember(member);
        System.out.println("계정생성: findMember = " + findMember);
        Integer deleteMember = adminMemberRepository.deleteMember(findMember.getMemberID());
        System.out.println("계정삭제: deleteMember = " + deleteMember);

        // then -> 되야함
        assertThat(deleteMember).isEqualTo(1);

    }
}