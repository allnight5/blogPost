package com.sparta.blogpost.controller;

import com.sparta.blogpost.dto.*;
import com.sparta.blogpost.jwt.JwtUtil;
import com.sparta.blogpost.security.UserDetailsImpl;
import com.sparta.blogpost.service.PostService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
//중복 매핑을 적어줌
@RequestMapping("/api")
public class PostController {
    //jwtUtil에 comment어느테이션을 넣어준이유는
    //Service에서 사용하기 위해서이다.
    private final PostService postService;
    private final JwtUtil jwtUtil;

    //1. 게시글 생성 API
    @ResponseBody
    @PostMapping("/post")
    public ResponseDto<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.createPost(requestDto, userDetails.getUser());
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
    public ResponseDto<PostResponseDto> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    //4. 선택한 게시글 수정 API
    @ResponseBody
    @PutMapping("/post/{id}")
    public MessageResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                return new MessageResponseDto("토큰이 존재하지 않습니다..", 400);
//            }
//        }else {
//            return new MessageResponseDto("토큰이 존재하지 않습니다..", 400);
//        }
//        String username = claims.getSubject();
        return postService.update(id, requestDto, userDetails.getUser());
    }

    //선택한 게시글 삭제 API
    @ResponseBody
    @DeleteMapping("/post/{id}")
    public MessageResponseDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String token = jwtUtil.resolveToken(request);
//
//        Claims claims;
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                return new MessageResponseDto("토큰이 존재하지 않습니다..", 400);
//            }
//        }else {
//            return new MessageResponseDto("토큰이 존재하지 않습니다..", 400);
//        }
//        String username = claims.getSubject();
        return postService.delete(id, userDetails.getUser());
    }
}