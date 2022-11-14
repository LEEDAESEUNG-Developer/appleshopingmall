package com.appleshopingmall.member;

import com.appleshopingmall.shop.product.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public int updateMember(Long memberId, MemberEntity member) {
        return memberRepository.updateMember(memberId, member);
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
    public int deleteMember(Long memberId, String email, String pwd){
        return memberRepository.deleteMember(memberId, email, pwd);
    }
}
