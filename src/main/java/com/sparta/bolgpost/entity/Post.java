package com.sparta.bolgpost.entity;

import com.sparta.bolgpost.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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
    private User user;
    public Post(PostRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
    public void addUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }
    public boolean isWriter(String username){
        if(Objects.equals(this.username, username)){
            return true;
        }
        return false;
    }
}