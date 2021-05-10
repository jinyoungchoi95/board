package com.board.board.category.dto;

import lombok.Getter;

@Getter
public class CategoryResponseDto {

    private final String name;

    public CategoryResponseDto(String name) {
        this.name = name;
    }
}
