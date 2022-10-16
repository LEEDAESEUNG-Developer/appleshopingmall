package com.appleshopingmall.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public int addMember(MemberEntity member) throws DuplicateKeyException {
        return memberRepository.addMember(member);
    }

    @Override
    public int updateMember(MemberEntity member) {
        return memberRepository.updateMember(member);
    }

    @Override
    public MemberEntity findMember(MemberEntity memberEntity) {
        return memberRepository.findMember(memberEntity);
    }

    @Override
    public MemberEntity findByMemberId(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    @Override
    public int deleteMember(MemberEntity member) {
        return memberRepository.deleteMember(member);
    }
}
