package com.appleshopingmall.member;

import org.apache.ibatis.annotations.Param;

public interface MemberRepository {

    // 회원 조회
    MemberEntity findMember(@Param("member") MemberEntity member);
}
