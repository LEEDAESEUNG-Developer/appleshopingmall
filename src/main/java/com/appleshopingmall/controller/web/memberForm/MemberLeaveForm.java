package com.appleshopingmall.controller.web.memberForm;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class MemberLeaveForm {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @Past(message = "생년월일 오류")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public MemberLeaveForm(String name, String password, String phoneNumber, String email, Date birthday) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
    }
}
