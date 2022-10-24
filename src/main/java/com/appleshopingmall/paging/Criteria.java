package com.appleshopingmall.paging;

import lombok.Data;

@Data
public class Criteria {

    private int pageNum; // 페이지 번호
    private int amount;  // 한 페이지당 몇개의 데이터 출력

    public Criteria(){
        this(1, 9);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
