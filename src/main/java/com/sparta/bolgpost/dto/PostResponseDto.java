package com.sparta.bolgpost.dto;
import com.sparta.bolgpost.entity.Post;
import com.sparta.bolgpost.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto{
        private Long id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public PostResponseDto (Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.author = post.getAuthor();
            this.content = post.getContent();
            this.createdDate = post.getCreatedDate();
            this.modifiedDate = post.getModifiedDate();
        }
    }