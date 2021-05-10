package com.board.board.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

class ConversionPageableTest {

    private int pagePostCount = 10;
    private Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");

    @Test
    @DisplayName("pageNum이 1이 들어왔을때 실제 Pageable num은 0이 되어야한다.")
    void testGetPageable() {
        //given
        Integer pageNum = 1;
        ConversionPageable conversionPageable = new ConversionPageable(pagePostCount, sort);

        //when
        Pageable pageable = conversionPageable.getPageable(pageNum);

        //then
        assertThat(pageable.getPageNumber()).isEqualTo(0);
        assertThat(pageable.getPageSize()).isEqualTo(pagePostCount);
        assertThat(pageable.getSort()).isEqualTo(sort);
    }

    @Test
    @DisplayName("pageNum이 1이상이 들어왔을때 실제 Pageable num은 pageNum-1이 되어야한다.")
    void testGetPagebleByPageNumIs5() {

        //given
        Integer pageNum = 5;
        ConversionPageable conversionPageable = new ConversionPageable(pagePostCount, sort);

        //when
        Pageable pageable = conversionPageable.getPageable(pageNum);

        //then
        assertThat(pageable.getPageNumber()).isEqualTo(pageNum-1);
    }

    @Test
    @DisplayName("전체 페이지 수를 초과하지않은 페이지를 요청받았을 때 요청된 페이지가 반환되어야 한다.")
    void testExchangePageable() {

        //given
        Integer pageNum = 10;
        int totalPage = 10;
        ConversionPageable conversionPageable = new ConversionPageable(pagePostCount, sort);
        Pageable oldPageable = conversionPageable.getPageable(pageNum);

        //when
        Pageable newPageable = conversionPageable.exchangePageable(oldPageable, totalPage);

        //then
        assertThat(newPageable.getPageNumber()).isEqualTo(pageNum-1);
    }

    @Test
    @DisplayName("전체 페이지 수를 초과한 페이지를 요청받았을 때 가장 마지막 페이지가 반환되어야 한다.")
    void testExchangePageableByMaxPageable() {

        //given
        Integer pageNum = 10;
        int totalPage = 5;
        ConversionPageable conversionPageable = new ConversionPageable(pagePostCount, sort);
        Pageable oldPageable = conversionPageable.getPageable(pageNum);

        //when
        Pageable newPageable = conversionPageable.exchangePageable(oldPageable, totalPage);

        //then
        assertThat(newPageable.getPageNumber()).isEqualTo(totalPage-1);
    }
}