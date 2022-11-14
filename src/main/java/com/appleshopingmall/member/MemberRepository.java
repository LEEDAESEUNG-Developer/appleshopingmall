package com.appleshopingmall.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

public interface MemberRepository {

    /**
     * 회원가입
     * @param member MemberEntity
     * @return 가입 성공 1, 실패 0
     * @throws DuplicateKeyException DuplicateKeyException
     */
    int addMember(@Param("member") MemberEntity member) throws DuplicateKeyException;

    /**
     * 회원 조회 로그인 (단일)
     * @param member MemberEntity
     * @return MemberEntity
     */
    MemberEntity findMember(@Param("member") MemberEntity member);

    /**
     * memberId(세션)으로 회원 정보를 가져오기
     * @param memberId Long
     * @return MemberEntity
     */
    MemberEntity findByMemberId(@Param("memberId") Long memberId);

    /**
     * 회원 수정
     * @param member MemberEntity
     * @return 성공시 1, 실패 0
     */
    int updateMember(@Param("memberId") Long memberId, @Param("member") MemberEntity member);

    /**
     * 회원 탈퇴
     * @param email 이메일
     * @param pwd 비밀번호
     * @return 삭제 성공 1, 실패 0
     */
    int deleteMember(@Param("memberId") Long memberId, @Param("email") String email, @Param("password") String pwd);

}
