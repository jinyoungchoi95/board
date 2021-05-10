package com.board.board.category.service;

import com.board.board.category.domain.Category;
import com.board.board.category.domain.CategoryRepository;
import com.board.board.category.dto.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.board.board.category.mapper.CategoryMapper.categoryMapper;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponseDto> findAllCategory() {

        return categoryMapper.entityToDto(categoryRepository.findAll());
    }

}
