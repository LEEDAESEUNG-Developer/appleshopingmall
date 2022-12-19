package com.appleshopingmall.controller.web.orderForm;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OrderAddForm {

    @NotBlank
    private String address;

    @NotBlank
    private String cardNo;

    // 카드 유효기간 5년
    @Min(value = 22)
    @Max(value = 27)
    private int year;

    @Min(value = 1)
    @Max(value = 12)
    private int month;

    @Min(value = 1, message = "오류가 있는 cvv 번호 입니다.")
    @Max(value = 999, message = "오류가 있는 cvv 번호 입니다.")
    private int cvvCode;

    @NotBlank
    private String bankName;
}