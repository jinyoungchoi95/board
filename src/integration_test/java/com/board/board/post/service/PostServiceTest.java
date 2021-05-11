package com.board.board.post.service;

import com.board.board.category.domain.Category;
import com.board.board.category.domain.CategoryRepository;
import com.board.board.post.domain.Post;
import com.board.board.post.domain.PostRepository;
import com.board.board.post.dto.PostTitleResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void cleanup(){
        postRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("페이지 번호가 1이 들어왔을 때 첫번째 페이지를 반환하여야 한다.")
    void testFindPostTitleByPageNumIs1() {

        //given
        Integer pageNum = 1;
        categoryRepository.save(Category.builder().name("category").build());
        Category category = categoryRepository.findAll().get(0);

        for(int i=1; i<=20; i++){
            postRepository.save(Post.builder().title("title" + i).content("content" + i).category(category).build());
        }

        //when
        Page<PostTitleResponseDto> dtos = postService.findPostTitleByPageNum(pageNum);
        System.out.println(dtos.getContent().get(1).getTitle());

        //then
        assertThat(dtos.getSize()).isEqualTo(15);
        assertThat(dtos.getNumber()).isEqualTo(0);
        assertThat(dtos.getContent().get(1).getTitle()).isEqualTo("title2");
        assertThat(dtos.getContent().get(1).getCategoryName()).isEqualTo("category");
        assertThat(dtos.nextPageable().getPageNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("특정 카테고리의 게시글 Pagenation만 반환되어야 한다.")
    void testFindPostTitleByPageNumAndCategory() {

        //given
        Integer pageNum = 1;
        categoryRepository.save(Category.builder().name("category1").build());
        categoryRepository.save(Category.builder().name("category2").build());

        Category category1 = categoryRepository.findAll().get(0);
        for(int i=1; i<=20; i++){
            postRepository.save(Post.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .category(category1)
                    .build());
        }
        Category category2 = categoryRepository.findAll().get(1);
        for(int i=21; i<=40; i++){
            postRepository.save(Post.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .category(category2)
                    .build());
        }

        //when
        Page<PostTitleResponseDto> dtos = postService.findPostTitleByPageNumAndCategory(pageNum, "category2");

        //then
        assertThat(dtos.getSize()).isEqualTo(15);
        assertThat(dtos.getNumber()).isEqualTo(0);
        assertThat(dtos.getContent().get(1).getTitle()).isEqualTo("title22");
        assertThat(dtos.getContent().get(1).getCategoryName()).isEqualTo("category2");
        assertThat(dtos.nextPageable().getPageNumber()).isEqualTo(1);
    }
}