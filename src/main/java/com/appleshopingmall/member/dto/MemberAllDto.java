package com.appleshopingmall.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;


@Getter
@ToString
@Builder
public class MemberAllDto {
    private Long memberId;
    private String memberFirstname;
    private String memberAddress;
    private Date memberBirthday;
    private String memberEmail;
    private String memberPwd;
    private String memberPhoneNumber;
    private String position;
}
