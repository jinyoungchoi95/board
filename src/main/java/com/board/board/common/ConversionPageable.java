package com.board.board.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ConversionPageable {

    private int pagePostCount;
    private Sort sort;

    public ConversionPageable(int pagePostCount, Sort sort) {
        this.pagePostCount = pagePostCount;
        this.sort = sort;
    }

    public Pageable getPageable(Integer pageNum) {
        Pageable paging = PageRequest.of(pageNum <= 1 ? 0 : pageNum - 1, pagePostCount, sort);
        return paging;
    }
    public Pageable exchangePageable(Pageable paging, int totalPage) {

        int pageNum = paging.getPageNumber();
        if(pageNum < totalPage) {
            return paging;
        }
        return getPageable(totalPage);
    }
}
