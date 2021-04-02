package com.board.board.service;

import com.board.board.domain.Post.Post;
import com.board.board.domain.Post.PostRepository;
import com.board.board.domain.Post.dto.PostTitleResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @AfterEach
    void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("PageNum_이 1이 들어왔을 때 첫번째 페이지 리턴하는지 확인")
    void testFindPostTitleByPageNum() {

        //given
        String title = "title";
        String content = "content";
        for (int i = 1; i <= 20; i++) {
            postRepository.save(Post.builder()
                    .title(title + i)
                    .content(content + i)
                    .build());
        }
        int pageNum = 1;

        //when
        Page<PostTitleResponseDto> postTitleResponseDtoPage = postService.findPostTitleByPageNum(pageNum);

        //then
        assertThat(postTitleResponseDtoPage.getSize()).isEqualTo(15);
        assertThat(postTitleResponseDtoPage.getNumber()).isEqualTo(0);
        assertThat(postTitleResponseDtoPage.getContent().get(1).getTitle()).isEqualTo("title2");
        assertThat(postTitleResponseDtoPage.nextPageable().getPageNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("PageNum_이 0이 들어왔을 때 정상 Page<>리턴하는지 확인")
    void testFindPostTitleByPageNumIs0() {

        //given
        String title = "title";
        String content = "content";
        postRepository.save(Post.builder()
                .title(title + 1)
                .content(content + 1)
                .build());
        int pageNum = 0;

        //when
        Page<PostTitleResponseDto> postTitleResponseDtoPage = postService.findPostTitleByPageNum(pageNum);

        //then
        assertThat(postTitleResponseDtoPage.getNumber()).isEqualTo(0);
        assertThat(postTitleResponseDtoPage.getContent().get(0).getTitle()).isEqualTo("title1");
    }

}