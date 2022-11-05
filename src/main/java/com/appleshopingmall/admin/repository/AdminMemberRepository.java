package com.appleshopingmall.admin.repository;

import com.appleshopingmall.member.MemberEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMemberRepository {

    // 회원 조회 (모두)
    List<MemberEntity> findMembers();

    // 회원 조회(단일)
    MemberEntity findMember(Long memberId);

    // 회원 삭제 (단일)
    int deleteMember(@Param("memberId") Long memberId);
}
