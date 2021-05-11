package com.board.board.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostTitleResponseDto {

    private final Long id;
    private final String title;
    private final String categoryName;
    private final LocalDateTime createdDate;

    public PostTitleResponseDto(Long id, String title, String categoryName, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.categoryName = categoryName;
        this.createdDate = createdDate;
    }
}