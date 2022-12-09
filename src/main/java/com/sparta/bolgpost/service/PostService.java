package com.sparta.bolgpost.service;

import com.sparta.bolgpost.dto.*;
import com.sparta.bolgpost.entity.Post;
import com.sparta.bolgpost.entity.User;
import com.sparta.bolgpost.enums.ErrorCode;
import com.sparta.bolgpost.jwt.JwtUtil;
import com.sparta.bolgpost.repository.PostRepository;
import com.sparta.bolgpost.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Getter
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    //게시글 생성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if(token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자 정보가 존재하지 않습니다."));

            Post post = postRepository.save(new Post(requestDto, user.getUsername(), user.getPassword(), user.getId()));
            return new PostResponseDto(post);
        }else
            return null;
    }
    //게시글 전체 목록 조회
    @Transactional(readOnly = true)
    public ResponseDto<List<Post>> getPosts() {
        List<Post> data;
        try {
            data = postRepository.findAllByOrderByModifiedDateDesc();
        }catch (IllegalArgumentException e) {
            return new ResponseDto<>(false,null, ErrorCode.ENTITY_NOT_FOUND);
        }
        return new ResponseDto<>(true, data);
        //return postRepository.findAllByOrderByModifiedDateDesc();
    }

    //선택한 게시글 조회
    @Transactional
    public ResponseDto<PostResponseDto> getPost(Long id) {
        Post post;
        try {
            post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            );
        }catch (IllegalArgumentException e){
            return new ResponseDto<>(false, null, ErrorCode.ENTITY_NOT_FOUND);
        }catch (Exception e){
            //실험용 다른 문자열
            return new ResponseDto<>(false, null, ErrorCode.PASSWORD_FALSE);
        }
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return new ResponseDto<>(true, postResponseDto);
    }
//    @Transactional
//    public ResponseDto<PostResponseDto> getPost(Long id) {
//        Post post;
//        try {
//            post = postRepository.findById(id).orElseThrow(
//                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
//            );
//        }catch (IllegalArgumentException e){
//            return new ResponseDto<>(false, null, ErrorCode.ENTITY_NOT_FOUND);
//        }catch (Exception e){
//            //실험용 다른 문자열
//            return new ResponseDto<>(false, null, ErrorCode.PASSWORD_FALSE);
//        }
//        PostResponseDto postResponseDto = new PostResponseDto(post);
//        return new ResponseDto<>(true, postResponseDto);
//    }

    //선택한 게시글 수정
    @Transactional
    public ResponseDto<PostResponseDto> update(Long id, PostRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("token error ");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시글에 대한 권한이 없습니다.")
            );
            post.update(requestDto, user.getUsername(), user.getPassword(), user.getId());
            return new ResponseDto<>(true, new PostResponseDto(post));
        }else{
            return null;
        }
    }
//    @Transactional
//    public ResponseDto<PostResponseDto> update(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        boolean result = requestDto.getPassword().equals(post.getPassword());
//        if(!result){
//            return new ResponseDto<>(false,null, ErrorCode.PASSWORD_FALSE);
//        }
//        post.update(requestDto);
//        return new ResponseDto<>(true, new PostResponseDto(post));
//    }

    //선택한 게시글 삭제
    @Transactional
    public MsgResponseDto delete(Long id, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims= jwtUtil.getUserInfoFromToken(token);
            }else {
                throw new IllegalArgumentException("token error");
            }
            Post post = postRepository.findById(id).orElseThrow(
                    ()-> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
            );
            if (!claims.getSubject().equals(post.getUsername())){
                MsgResponseDto msg = new MsgResponseDto("삭제실패", HttpStatus.FAILED_DEPENDENCY.value());
                return msg;
            }
            postRepository.delete(post);
            MsgResponseDto msg = new MsgResponseDto("삭제 성공", HttpStatus.OK.value());
            return msg;

        }else {
            MsgResponseDto msg = new MsgResponseDto("삭제 실패", HttpStatus.FAILED_DEPENDENCY.value());

            return msg;
        }
    }

//    @Transactional
//    public DeleteResponseDto delete(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                ()-> new NullPointerException("해당 아이디가 존재하지 않습니다.")
//        );
//        boolean result = requestDto.getPassword().equals(post.getPassword());
//        boolean reponse;
//        if (result) {
//            postRepository.deleteById(id);
//            reponse = true;
//        }else{
//            reponse = false;
//        }
//        return new DeleteResponseDto(reponse);
//    }
}