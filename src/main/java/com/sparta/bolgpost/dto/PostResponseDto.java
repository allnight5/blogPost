package com.sparta.bolgpost.dto;
import com.sparta.bolgpost.entity.Post;
import com.sparta.bolgpost.entity.Timestamped;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto{
        private Long id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public PostResponseDto (Post post) {
//            super.createdAt = post.getCreatedAt();
//            super.modifiedAt = post.getModifiedAt();
            this.id = post.getId();
            this.title = post.getTitle();
            this.author = post.getAuthor();
            this.content = post.getContent();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
        }
    }