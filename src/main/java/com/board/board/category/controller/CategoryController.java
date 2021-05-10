package com.board.board.category.controller;

import com.board.board.category.dto.CategoryResponseDto;
import com.board.board.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String findAllCategory(Model model) {
        List<CategoryResponseDto> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);

        return "category-form";
    }
}
