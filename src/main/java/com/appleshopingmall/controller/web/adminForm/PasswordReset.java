package com.appleshopingmall.controller.web.adminForm;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class PasswordReset {

    @NotBlank
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;
}
