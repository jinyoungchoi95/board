package com.board.board.post.mapper;

import com.board.board.category.domain.Category;
import com.board.board.post.domain.Post;
import com.board.board.post.dto.PostTitleResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.board.board.post.mapper.PostMapper.postMapper;
import static org.assertj.core.api.Assertions.assertThat;

class PostMapperTest {

    @Test
    @DisplayName("Post / Entity to Dto 매핑 확인")
    void testEntityToTitleDto() {

        //given
        Category category = Category.builder().id(1L).name("category").build();
        Post post = Post.builder().id(1L).title("title").content("content")
                .category(category).createdDate(LocalDateTime.now()).build();

        //when
        PostTitleResponseDto dto = postMapper.entityToTitleDto(post);

        //then
        assertThat(dto.getCreatedDate()).isNotNull();
        assertThat(dto.getId()).isEqualTo(post.getId());
        assertThat(dto.getTitle()).isEqualTo(post.getTitle());
        assertThat(dto.getCategoryName()).isEqualTo(post.getCategory().getName());
        assertThat(dto.getCreatedDate()).isEqualTo(post.getCreatedDate());
    }

    @Test
    @DisplayName("Post / Entity to Dto 매핑 확인 - null")
    void testEntityToTitleDtoByNullPost() {

        //given
        Post post = null;

        //when
        PostTitleResponseDto dto = postMapper.entityToTitleDto(post);

        //then
        assertThat(dto).isNull();
    }
}