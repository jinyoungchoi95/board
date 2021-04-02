package com.board.board.domain.Post.dto;

import com.board.board.domain.Post.Post;
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
