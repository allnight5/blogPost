package com.sparta.blogpost.dto;


import com.sparta.blogpost.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String username;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.username = comment.getUsername();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate= comment.getModifiedDate();
    }
}
