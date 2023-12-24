package com.example.news.repository;

import com.example.news.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseCommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findAllByNewsId(Long newsId);
}
