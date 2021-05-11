package com.board.board.post.domain;

import com.board.board.category.domain.Category;
import com.board.board.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Post(Long id, String title, String content, Category category, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = createdDate;
    }
}
