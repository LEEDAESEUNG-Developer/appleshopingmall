package com.appleshopingmall.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

public interface MemberRepository {

    // 회원 조회 (로그인)
    MemberEntity findMember(@Param("member") MemberEntity member);

    // 회원 가입
    int addMember(@Param("member") MemberEntity member) throws DuplicateKeyException;

    // 회원 탈퇴
    int deleteMember(@Param("member") MemberEntity member);

}
