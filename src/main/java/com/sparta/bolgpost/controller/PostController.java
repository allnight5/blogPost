package com.sparta.bolgpost.controller;

import com.sparta.bolgpost.dto.*;
import com.sparta.bolgpost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
//중복 매핑을 적어줌
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //1. 게시글 생성 API
    @ResponseBody
    @PostMapping("/post")
    public ResponseDto<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    // 2. 게시글 전체 목록 조회 API
    @ResponseBody
    @GetMapping("/post")
    public List<PostResponseDto> getPostList() {

        return postService.getPosts();
    }

    // 3. 선택한 게시글 조회 API
    @ResponseBody
    @GetMapping("/post/{id}")
    public ResponseDto<List<PostResponseDto>> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    //4. 선택한 게시글 수정 API
    @ResponseBody
    @PutMapping("/post/{id}")
    public MessageResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.update(id, requestDto, request);
    }

    //선택한 게시글 삭제 API
    @ResponseBody
    @DeleteMapping("/post/{id}")
    public MessageResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.delete(id, request);
    }
}