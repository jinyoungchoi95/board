package com.board.board.post.service;

import com.board.board.post.domain.Post;
import com.board.board.post.domain.PostRepository;
import com.board.board.post.dto.PostTitleResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("페이지 번호가 1이 들어왔을 때 첫번째 페이지를 반환하여야 한다.")
    void testFindPostTitleByPageNumIs1() {

        //given
        Integer pageNum = 1;
        for(int i=1; i<=20; i++){
            postRepository.save(Post.builder().title("title" + i).content("content" + i).build());
        }

        //when
        Page<PostTitleResponseDto> dtos = postService.findPostTitleByPageNum(pageNum);
        System.out.println(dtos.getContent().get(1).getTitle());

        //then
        assertThat(dtos.getSize()).isEqualTo(15);
        assertThat(dtos.getNumber()).isEqualTo(0);
        assertThat(dtos.getContent().get(1).getTitle()).isEqualTo("title2");
        assertThat(dtos.nextPageable().getPageNumber()).isEqualTo(1);
    }
}