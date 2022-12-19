package com.appleshopingmall.controller.web.memberForm;

import lombok.Data;

@Data
public class MemberPasswordResetForm {
    private String password;
    private String passwordChk;
}
