package com.board.board.post.service;

import com.board.board.category.domain.Category;
import com.board.board.category.domain.CategoryRepository;
import com.board.board.common.ConversionPageable;
import com.board.board.post.domain.Post;
import com.board.board.post.domain.PostRepository;
import com.board.board.post.dto.PostTitleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.board.board.post.mapper.PostMapper.postMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    private static final int PAGE_POST_COUNT = 15;

    @Transactional
    public Page<PostTitleResponseDto> findPostTitleByPageNum(Integer pageNum) {
        ConversionPageable conversionPageable = new ConversionPageable(PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate"));

        Pageable pageable = conversionPageable.getPageable(pageNum);
        Page<Post> posts = postRepository.findAll(pageable);
        Pageable pageRequest = conversionPageable.exchangePageable(pageable, posts.getTotalPages());
        return postRepository.findAll(pageRequest).map(postMapper::entityToTitleDto);
    }

    @Transactional
    public Page<PostTitleResponseDto> findPostTitleByPageNumAndCategory(Integer pageNum, String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        ConversionPageable conversionPageable = new ConversionPageable(PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate"));

        Pageable pageable = conversionPageable.getPageable(pageNum);
        Page<Post> posts = postRepository.findByCategory(category, pageable);
        Pageable pageRequest = conversionPageable.exchangePageable(pageable, posts.getTotalPages());
        return postRepository.findByCategory(category, pageRequest).map(postMapper::entityToTitleDto);
    }

}
