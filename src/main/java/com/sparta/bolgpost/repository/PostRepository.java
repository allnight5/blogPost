package com.sparta.bolgpost.repository;

import com.sparta.bolgpost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PostRepository extends JpaRepository<Post, Long> {

    // List 에 모든 게시글을 담아서 가져온다.
    List<Post> findAllByOrderByModifiedAtDesc();

}
