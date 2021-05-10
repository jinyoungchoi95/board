package com.board.board.post.controller;

import com.board.board.post.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .alwaysExpect(MockMvcResultMatchers.status().isOk())
                .build();
    }
    @Test
    @DisplayName("/posts/{pageNum} url요청이 왔을 때 'posts' 뷰 네임이 반환되어야 한다.")
    void testFindPostTitleByPageNum() throws Exception {

        this.mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("posts-form"));
    }
    @Test
    @DisplayName(("/posts url요청이 왔을 때 'posts' 뷰 네임이 반환되어야 한다."))
    void TestFindPostTitleByNull() throws Exception {

        this.mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(view().name("posts-form"));
    }
}