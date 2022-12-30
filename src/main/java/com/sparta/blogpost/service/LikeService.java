package com.sparta.blogpost.service;


import com.sparta.blogpost.repository.CommentRepository;
import com.sparta.blogpost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    //좋아요 증가

}
