package com.sparta.bolgpost.service;

import com.sparta.bolgpost.dto.DeleteResponseDto;
import com.sparta.bolgpost.dto.PostRequestDto;
import com.sparta.bolgpost.dto.PostResponseDto;
import com.sparta.bolgpost.entity.Post;
import com.sparta.bolgpost.repository.PostRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //게시글 생성
    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }
    //게시글 전체 목록 조회
    @Transactional(readOnly = true)
    public List<Post> getPosts() {

        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    //선택한 게시글 조회
    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    //선택한 게시글 수정
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        boolean result = requestDto.getPassword().equals(post.getPassword());

        post.update(requestDto);
        return new PostResponseDto(post);
    }
    //선택한 게시글 삭제
    @Transactional
    public DeleteResponseDto delete(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        boolean result = requestDto.getPassword().equals(post.getPassword());
        boolean reponse;
        if (result) {
            postRepository.deleteById(id);
            reponse = true;
        }else{
            reponse = false;
        }
        return new DeleteResponseDto(reponse);
    }
}