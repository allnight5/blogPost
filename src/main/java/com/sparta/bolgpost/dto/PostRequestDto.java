package com.sparta.bolgpost.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostRequestDto{
    private String title;
    private String content;
    private String author;
    private String password;
}