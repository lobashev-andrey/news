package com.example.news.service;

import com.example.news.filter.CommentFilter;
import com.example.news.model.Comment;
import com.example.news.repository.DatabaseCommentRepository;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseCommentService implements CommentService{

    private final DatabaseCommentRepository repository;
    @Override
    public List<Comment> findAllByNewsId(CommentFilter filter) {
        return repository.findAllByNewsId(filter.getNewsId());
    }

    @Override
    public Comment findById(Long id) {
        Comment existedComment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Комментарий с ID {0} не найден", id)));
        return existedComment;
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());
        BeanUtils.nonNullPropertiesCopy(comment, existedComment);

        repository.save(existedComment);

        return existedComment;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
