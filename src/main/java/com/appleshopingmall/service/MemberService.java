package com.appleshopingmall.service;

import com.appleshopingmall.entity.MemberEntity;
import org.springframework.dao.DuplicateKeyException;

public interface MemberService {

    /**
     * 회원가입
     * @param member MemberEntity
     * @return 가입 성공 1, 실패 0
     * @throws DuplicateKeyException DuplicateKeyException
     */
    int addMember(MemberEntity member) throws DuplicateKeyException;

    /**
     * 회원 조회 로그인 (단일)
     * @param member MemberEntity
     * @return MemberEntity
     */
    MemberEntity findMember(MemberEntity member);

    /**
     * memberId(세션)으로 회원 정보를 가져오기
     * @param memberId Long
     * @return MemberEntity
     */
    MemberEntity findByMemberId(Long memberId);

    /**
     * 회원 수정
     * @param memberId memberId
     * @param member MemberEntity
     * @return 성공시 1, 실패 0
     */
    int updateMember(Long memberId, MemberEntity member);

    /**
     * 회원 탈퇴
     * @param email 이메일
     * @param pwd 비밀번호
     * @return 삭제 성공 1, 실패 0
     */
    int deleteMember(Long memberId, String email, String pwd);

    /**
     * 이메일 중복 검사
     * @param email String
     * @return 가입가능 T, 불가능 F
     */
    boolean emailCheck(String email);

    /**
     * 이메일이 있는 검색
     * @param email String
     * @return 있으면 T, 없으면 F
     */
    boolean findByEmail(String email);

    /**
     * 회원 비밀번호 찾기 (비밀번호 수정)
     * @param email String
     * @param password String
     * @return 성공시 T, 실패시 F
     */
    boolean updateMemberPassword(String email, String password);

}
