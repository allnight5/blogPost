package com.sparta.bolgpost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.bolgpost.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped{
//    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
//        super.createdAt = requestDto.getCreatedAt();
//        super.modifiedAt = requestDto.getModifiedAt();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }
}