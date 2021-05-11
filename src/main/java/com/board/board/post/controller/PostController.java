package com.board.board.post.controller;

import com.board.board.post.dto.PostTitleResponseDto;
import com.board.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping({"/posts", "/posts/{pageNum}"})
    public String findPostTitleByPageNum(@PathVariable Optional<Integer> pageNum, Model model) {
        Integer newPageNum = pageNum.isPresent()? pageNum.get() : 0;
        Page<PostTitleResponseDto> posts = postService.findPostTitleByPageNum(newPageNum);
        model.addAttribute("posts", posts);

        return "posts-form";
    }

    @GetMapping("/category/posts")
    public String findPostTitleByPageNumAndCategory(@RequestParam String category, @RequestParam(defaultValue = "0") Integer pageNum, Model model) {
        Page<PostTitleResponseDto> posts = postService.findPostTitleByPageNumAndCategory(pageNum, category);
        model.addAttribute("posts", posts);

        return "posts-by-category-form";
    }
}
