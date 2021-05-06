package com.board.board.post.dto;

import com.board.board.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostTitleResponseDto {

    private final Long id;
    private final String title;
    private final LocalDateTime createdDate;

    public PostTitleResponseDto(Long id, String title, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
    }
}
