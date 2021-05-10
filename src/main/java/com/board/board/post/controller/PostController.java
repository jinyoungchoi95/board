package com.board.board.post.controller;

import com.board.board.post.dto.PostTitleResponseDto;
import com.board.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
