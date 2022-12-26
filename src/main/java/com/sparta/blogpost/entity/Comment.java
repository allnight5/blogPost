package com.sparta.blogpost.entity;

import com.sparta.blogpost.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    /*
        여기서 이제.. 댓글은 유저와 다대일 관계가 된다.
        댓글은. 게시글과 다대일 관계가 된다.
        댓글이 Many이며 유저와 게시글이 One이다
        MnayToOne
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    Long id;

    @Column(nullable = false)
    String comment;

    String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post posts;

    public Comment(CommentRequestDto requestDto, String username){
        this.comment = requestDto.getComment();
        this.username = username;
    }

    public void update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }

    public void addUserAndPost(User user, Post post){
        this.user = user;
        this.posts = post;
        posts.getCommentList().add(this);
    }
    public boolean isWriter(String username){
        if(Objects.equals(this.username, username)){
            return true;
        }
        return false;
    }
}
