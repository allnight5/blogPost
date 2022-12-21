package com.sparta.bolgpost.service;

import com.sparta.bolgpost.dto.*;
import com.sparta.bolgpost.entity.Comment;
import com.sparta.bolgpost.entity.Post;
import com.sparta.bolgpost.entity.User;
import com.sparta.bolgpost.enums.UserRoleEnum;
import com.sparta.bolgpost.jwt.JwtUtil;
import com.sparta.bolgpost.repository.CommentRepository;
import com.sparta.bolgpost.repository.PostRepository;
import com.sparta.bolgpost.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;
    private MessageResponseDto msg;

    //게시글 생성
    @Transactional
    public ResponseDto<PostResponseDto> createPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if(token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else {
                return new ResponseDto<>("토큰이 정확하지 않습니다...", 400);
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자 정보가 존재하지 않습니다."));

            Post post = new Post(requestDto, user.getUsername());
            post.addUser(user);
            postRepository.save(post);
            return new ResponseDto<>(new PostResponseDto(post));
        }else {
            return null;
        }
    }
    //게시글 전체 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> data = postRepository.findAllByOrderByModifiedDateDesc();

        List<PostResponseDto> result = new ArrayList<>();
        for (Post post : data){
            List<Comment> comments = commentRepository.findAllByPosts(post);
            PostResponseDto postResponseDto = new PostResponseDto(post);
            List<CommentResponseDto> commentResponseDto = new ArrayList<>();
            for(Comment comment : comments){
                commentResponseDto.add(new CommentResponseDto(comment));
            }
            postResponseDto.setCommentList(commentResponseDto);
            result.add(postResponseDto);
        }

        return result;
    }

    //선택한 게시글 조회
    @Transactional
    public ResponseDto<List<PostResponseDto>> getPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            return new ResponseDto<>("해당 게시글이 없습니다..", 400);
        }
        List<PostResponseDto> result = new ArrayList<>();
        List<Comment> comments = commentRepository.findAllByPosts(post.get());
        PostResponseDto postResponseDto = new PostResponseDto(post.get());
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();
        for(Comment comment : comments){
            commentResponseDto.add(new CommentResponseDto(comment));
        }
        postResponseDto.setCommentList(commentResponseDto);
        result.add(postResponseDto);
        return new ResponseDto<>(result);
    }
    //선택한 게시글 수정
    @Transactional
    public MessageResponseDto update(Long id, PostRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                return new MessageResponseDto("토큰이 존재하지 않습니다..", 400);
            }
            Optional<User> user = userRepository.findByUsername(claims.getSubject());
            if(user.isEmpty()) {
                return new MessageResponseDto("해당 게시글에 수정 대한 권한이 없습니다.", 400);
            }
            Optional<Post> post = postRepository.findById(id);
            if(post.isEmpty()) {
//                return new MessageResponseDto("존재하지 않는 게시글입니다.", HttpStatus.FAILED_DEPENDENCY.value());
                return new MessageResponseDto("존재하지 않는 게시글입니다.", 400);
            }
            if(user.get().getUsername().equals(post.get().getUsername()) || user.get().getRole() == UserRoleEnum.ADMIN) {
                post.get().update(requestDto);
                return new MessageResponseDto("수정 성공", HttpStatus.OK.value());
            }
            return new MessageResponseDto("수정 실패.", 400);
        }else{
            return new MessageResponseDto("수정 실패.", 400);
        }
    }

    //선택한 게시글 삭제
    @Transactional
    public MessageResponseDto delete(Long id, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims= jwtUtil.getUserInfoFromToken(token);
            }else {
                return new MessageResponseDto("토큰이 유효하지 않습니다.", 400);
            }
            Optional<User> user = userRepository.findByUsername(claims.getSubject());
            if(user.isEmpty()) {
                return new MessageResponseDto("해당 게시글에 대한 권한이 없습니다.", 400);
            }
            Optional<Post> post = postRepository.findById(id);
            if(post.isEmpty()) {
                return new MessageResponseDto("존재하지 않는 게시글입니다.", HttpStatus.FAILED_DEPENDENCY.value());
            }
            if(user.get().getUsername().equals(post.get().getUsername()) || user.get().getRole() == UserRoleEnum.ADMIN) {
                postRepository.delete(post.get());
                return new MessageResponseDto("삭제 성공", HttpStatus.OK.value());
            }
//            return new MsgResponseDto("삭제 성공", HttpStatus.OK.value());
        }
        return new MessageResponseDto("삭제 실패", HttpStatus.FAILED_DEPENDENCY.value());
    }

}