package com.appleshopingmall.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    // member 테이블 전체 조회
    MemberEntity findMember(@Param("member") MemberEntity member);
}
