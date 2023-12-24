package com.example.news.repository;

import com.example.news.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAllByNewsId(Long newsId);

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void delete(Long id);

    void deleteList(List<Comment> list);
}
