package com.sparta.bolgpost.dto;
import com.sparta.bolgpost.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto{
        private Long id;
        private String title;
        private String content;
        private String username;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public PostResponseDto (Post post) {
            this.id = post.getId();
            this.createdDate = post.getCreatedDate();
            this.modifiedDate = post.getModifiedDate();
            this.title = post.getTitle();
            this.username = post.getUsername();
            this.content = post.getContent();
        }
    }