package com.appleshopingmall.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public MemberEntity findMember(MemberEntity memberEntity) {
        return memberMapper.findMember(memberEntity);
    }
}
