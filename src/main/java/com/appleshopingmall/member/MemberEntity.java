package com.appleshopingmall.member;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
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
