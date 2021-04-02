package com.board.board.controller;

import com.board.board.domain.Post.dto.PostTitleResponseDto;
import com.board.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/Post")
    public Page<PostTitleResponseDto> findPostTitleByPageNum(Integer pageNum) {

        return postService.findPostTitleByPageNum(pageNum);
    }
}
