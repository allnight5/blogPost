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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long userId;
    public Post(PostRequestDto requestDto, String username, String password, Long userId) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public void update(PostRequestDto requestDto, String username, String password, Long userId) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
        this.password = password;
        this.userId = userId;
    }
}