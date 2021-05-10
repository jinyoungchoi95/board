package com.board.board.category.service;

import com.board.board.category.domain.Category;
import com.board.board.category.domain.CategoryRepository;
import com.board.board.category.dto.CategoryResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private List<Category> categories = Arrays.asList(
            Category.builder().id(1L).name("category1").build(),
            Category.builder().id(2L).name("category2").build(),
            Category.builder().id(3L).name("category3").build()
    );

    @Test
    @DisplayName("모든 카테고리 목록이 반환되어야 한다.")
    void findAllCategory() {

        //setUp
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryResponseDto> dtos = categoryService.findAllCategory();

        //then
        assertThat(dtos.get(1).getName()).isEqualTo("category2");
    }
}