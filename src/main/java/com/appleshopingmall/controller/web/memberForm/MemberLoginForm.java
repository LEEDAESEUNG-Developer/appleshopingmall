package com.appleshopingmall.controller.web.memberForm;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginForm {

//    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    //아이디 저장 체크
    private boolean idSaveChk;
}
