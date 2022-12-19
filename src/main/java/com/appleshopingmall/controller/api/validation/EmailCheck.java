package com.appleshopingmall.controller.api.validation;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class EmailCheck {

    @Email
    private String email;
}
