package com.example.news.repository.impl;

import com.example.news.model.Comment;
import com.example.news.model.News;
import com.example.news.repository.CommentRepository;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final List<Comment> comments = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public List<Comment> findAll() {
        return comments;
    }

    @Override
    public Comment findById(Long id) {
        return comments.stream().filter(
                        (n)-> Objects.equals(n.getId(), id))
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException(
                        MessageFormat.format("Комментарий с ID {0} не найден", id)));
    }

    @Override
    public Comment save(Comment comment) {
        comment.setId(currentId.getAndIncrement());
        comments.add(comment);

        News news = comment.getNews();
        news.getComments().add(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        Long id = comment.getId();
        Comment existedComment = findById(id);
        if(existedComment == null) {
            throw new EntityNotFoundException(MessageFormat.format("Комментарий с ID {0} не найден.", id));
        }
        BeanUtils.nonNullPropertiesCopy(comment, existedComment);
        return existedComment;
    }

    @Override
    public void delete(Long id) {
        Comment comment = findById(id);

        News news = comment.getNews();
        news.getComments().remove(comment);

        comments.remove(comment);
    }

    @Override
    public void deleteList(List<Comment> list) {
        list.forEach(comments::remove);
    }
}