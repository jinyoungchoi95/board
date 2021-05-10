package com.board.board.category.mapper;

import com.board.board.category.domain.Category;
import com.board.board.category.dto.CategoryResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.board.board.category.mapper.CategoryMapper.categoryMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    @Test
    @DisplayName("Category / Entity to Dto 매핑 확인")
    void testEntityToDto() {

        //given
        Category category = Category.builder().id(1L).name("category").build();

        //when
        CategoryResponseDto dto = categoryMapper.entityToDto(category);

        //then
        assertThat(dto.getName()).isEqualTo(category.getName());
    }

    @Test
    @DisplayName("Category / Entity to Dto 매핑 확인 - null")
    void testEntityToDtoByNullCategory() {

        //given
        Category category = null;

        //when
        CategoryResponseDto dto = categoryMapper.entityToDto(category);

        //then
        assertThat(dto).isNull();
    }

    @Test
    @DisplayName("CategoryList / Entity to Dto 매핑 확인")
    void testEntityToBtoList() {

        //given
        List<Category> categories = Arrays.asList(
                Category.builder().id(1L).name("category1").build(),
                Category.builder().id(2L).name("category2").build(),
                Category.builder().id(3L).name("category3").build()
        );

        //when
        List<CategoryResponseDto> dtos = categoryMapper.entityToDto(categories);

        //then
        assertThat(dtos.get(1).getName()).isEqualTo(categories.get(1).getName());
    }

    @Test
    @DisplayName("CategoryList / Entity to Dto 매핑 확인 - null")
    void testEntityToBtoListByNull() {

        //given
        List<Category> categories = null;

        //when
        List<CategoryResponseDto> dtos = categoryMapper.entityToDto(categories);

        //then
        assertThat(dtos).isNull();
    }
}