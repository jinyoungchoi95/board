package com.board.board.domain.Post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("Post_등록확인")
    void testSavePost() {

        //given
        String title = "title1";
        String content = "content2";
        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .build());

        //when
        List<Post> posts = postRepository.findAll();
        Post post = posts.get(0);

        //then
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("BaseTimeEntity_Post_상속확인")
    void testBaseTimeEntityInPost() {

        //given
        LocalDateTime beforeTime = LocalDateTime.now(Clock.systemDefaultZone());
        postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .build());
        LocalDateTime afterTime = LocalDateTime.now(Clock.systemDefaultZone());

        //when
        List<Post> posts = postRepository.findAll();
        Post post = posts.get(0);

        //then
        assertThat(post.getCreatedDate()).isAfter(beforeTime);
        assertThat(post.getCreatedDate()).isBefore(afterTime);
        System.out.println("beforeTime : " + beforeTime);
        System.out.println("afterTime : " + afterTime);
        System.out.println("postTime : " + post.getCreatedDate());
    }
}