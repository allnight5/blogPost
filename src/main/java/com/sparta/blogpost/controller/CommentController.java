package com.sparta.blogpost.controller;


import com.sparta.blogpost.dto.*;
import com.sparta.blogpost.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController//CONTROLLER VS RESTCONTROLLER 두개의 차이점
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //1. 댓글 생성 API
    @ResponseBody
    @PostMapping("/{id}")
    public ResponseDto<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id) {
        return commentService.createComment(requestDto, request, id);
    }
    //2.댓글 수정 API
    @ResponseBody
    @PutMapping("/{id}")
    public MessageResponseDto updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id) {
        return commentService.updateComment(requestDto, request, id);
    }
    //3. 댓글 삭제 API
    @ResponseBody
    @DeleteMapping("/{id}")
    public MessageResponseDto deleteComment(HttpServletRequest request, @PathVariable Long id) {
        return commentService.deleteComment(request, id);
    }
}
