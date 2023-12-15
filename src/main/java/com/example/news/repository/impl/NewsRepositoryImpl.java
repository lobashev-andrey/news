package com.example.news.repository.impl;

import com.example.news.model.News;
import com.example.news.model.User;
import com.example.news.repository.CommentRepository;
import com.example.news.repository.NewsRepository;
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
public class NewsRepositoryImpl implements NewsRepository {

    private final  List<News> newsList = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(1);

    private final CommentRepository commentRepository;

    @Override
    public List<News> findAll() {
        return newsList;
    }

    @Override
    public News findById(Long id) {
        return newsList.stream().filter(
                (n)-> Objects.equals(n.getId(), id))
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException(
                        MessageFormat.format("Новость с ID {0} не найдена", id)));
    }

    @Override
    public News save(News news) {
        news.setId(currentId.getAndIncrement());
        newsList.add(news);
        User user = news.getUser();
        user.getNews().add(news);
        return news;
    }

    @Override
    public News update(News news) {
        Long id = news.getId();
        News existedNews = findById(id);
        if(existedNews == null) {
            throw new EntityNotFoundException(MessageFormat.format("Новость с ID {0} не найдена.", id));
        }
        BeanUtils.nonNullPropertiesCopy(news, existedNews);
        return existedNews;
    }

    @Override
    public void delete(Long id) {
        News news = findById(id);

        User user = news.getUser();
        user.getNews().remove(news);

        commentRepository.deleteList(news.getComments());

        newsList.remove(news);
    }

    @Override
    public void deleteList(List<News> list) {
        for(News news : list){
            commentRepository.deleteList(news.getComments());
            newsList.remove(news);
        }
    }


}
