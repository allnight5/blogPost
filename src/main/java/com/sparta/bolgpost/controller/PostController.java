package com.sparta.bolgpost.controller;

import com.sparta.bolgpost.dto.DeleteResponseDto;
import com.sparta.bolgpost.dto.PostRequestDto;
import com.sparta.bolgpost.dto.PostResponseDto;
import com.sparta.bolgpost.dto.ResponseDto;
import com.sparta.bolgpost.entity.Post;
import com.sparta.bolgpost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//중복 매핑을 적어줌
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //1. 게시글 생성
    @PostMapping("/post")
    public Post createMemo(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 2. 게시글 전체 목록 조회
    @GetMapping("/post")
    public ResponseDto<List<Post>> getPostList() {
        return postService.getPosts();
    }

    // 3. 선택한 게시글 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    //4. 선택한 게시글 수정
    @PutMapping("/post/{id}")
    public ResponseDto<PostResponseDto> updateMemo(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    //선택한 게시글 삭제
    @DeleteMapping("/post/{id}")
    public DeleteResponseDto deleteMemo(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.delete(id, requestDto);
    }
}