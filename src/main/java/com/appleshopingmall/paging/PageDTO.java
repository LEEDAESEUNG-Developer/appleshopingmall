package com.appleshopingmall.paging;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 데이터베이스에 조회 및 페이징 적용
 */
@Slf4j
@Data
public class PageDTO {
    private int startPage;              // 시작 페이지
    private int endPage;                // 끝 페이지
    private boolean prev, next;         // 이전, 다음

    private Criteria cri;               // pageNum, amount
    private int total;                  // 테이블 전체 row

    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        log.info("계산하기 전 endPage = {}, startPage = {}", endPage, startPage);

        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        this.startPage = this.endPage - 9;
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        log.info("계산하기 후 endPage = {}, startPage = {}", endPage, startPage);
        log.info("cri.getPageNum() = {} ", cri.getPageNum());

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;

    }
}
