package com.appleshopingmall.member.web.form;

import lombok.Data;

@Data
public class MemberPasswordResetForm {
    private String password;
    private String passwordChk;
}
