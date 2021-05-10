package com.board.board.category.mapper;

import com.board.board.category.domain.Category;
import com.board.board.category.dto.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    CategoryResponseDto entityToDto(Category category);
    List<CategoryResponseDto> entityToDto(List<Category> categories);
}
