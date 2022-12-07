package com.sparta.bolgpost.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class PostRequestDto{
    private String title;
    private String content;
    private String author;
    private String password;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;


}