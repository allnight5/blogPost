package com.sparta.blogpost.entity;

import com.sparta.blogpost.dto.PostRequestDto;
import com.sparta.blogpost.dto.UserResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
//    @ManyToOne
    private String username;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Comment> commentList = new ArrayList<>();

    //게시글 참조하는 User관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users;

//    public Post(PostRequestDto requestDto, User user) {
//        this.title = requestDto.getTitle();
//        this.content = requestDto.getContent();
//        this.user = user;
//    }

    public Post(PostRequestDto requestDto, String username, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
        this.users = user;

    }
    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void addUser(User user){
        this.users = user;
//        users.getPosts().add(this);
    }
    public boolean isWriter(String username){
        if(Objects.equals(this.username, username)){
            return true;
        }
        return false;
    }
}