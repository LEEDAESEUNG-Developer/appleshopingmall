package com.appleshopingmall.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

public interface MemberService {

    // 회원 조회
    MemberEntity findMember(MemberEntity memberEntity);

    // 회원 가입
    int addMember(MemberEntity member) throws DuplicateKeyException;

    // 회원 탈퇴
    int deleteMember(MemberEntity member);

}
