package com.appleshopingmall.controller.web;

import lombok.Data;

/**
 * 웹 쿼리 파라미터에 적용 (객체)
 */
@Data
public class Criteria {

    private int pageNum;    // 페이지 번호
    private int amount;     // 페이지당 데이터를 몇 개 출력할지
    private String type;    // 맥북, 아이패드, 아이폰
    private String ap;      // ap
    private String color;   //색상

    public Criteria(){
        this(1, 9);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
