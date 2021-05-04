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

    @Builder
    public PostTitleResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.createdDate = post.getCreatedDate();
    }
}
