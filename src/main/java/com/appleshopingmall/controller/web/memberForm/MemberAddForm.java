package com.appleshopingmall.controller.web.memberForm;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class MemberAddForm {
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

//    @Email
    private String email;

    @Past(message = "생년월일 오류")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotBlank
    private String address;
}
