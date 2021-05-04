package com.board.board.post.service;

import com.board.board.post.domain.PostRepository;
import com.board.board.post.dto.PostTitleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private static final int PAGE_POST_COUNT = 15;

    @Transactional
    public Page<PostTitleResponseDto> findPostTitleByPageNum(Integer pageNum) {

        return postRepository.findAll(PageRequest.of(pageNum<=1? 0:pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")))
                .map(PostTitleResponseDto::new);
    }
}
