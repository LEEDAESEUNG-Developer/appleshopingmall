package com.appleshopingmall.member.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginForm {

//    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
