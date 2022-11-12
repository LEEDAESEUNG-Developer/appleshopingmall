package com.appleshopingmall.member;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@ToString
@Builder
public class MemberEntity {
    private Long memberID;
    private String memberFirstname;
    private String memberAddress;
    private Date memberBirthday;
    private String memberEmail;
    private String memberPwd;
    private String memberPhoneNumber;
    private String position;
}
