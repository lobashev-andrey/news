package com.example.news.service;

import com.example.news.filter.CommentFilter;
import com.example.news.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByNewsId(CommentFilter filter);


    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void delete(Long id);
}
