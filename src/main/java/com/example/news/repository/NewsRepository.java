package com.example.news.repository;

import com.example.news.model.News;
import org.springframework.stereotype.Component;

import java.util.List;

public interface NewsRepository {

    List<News> findAll();

    News findById(Long id);

    News save(News news);

    News update(News news);

    void delete(Long id);

    void deleteList(List<News> list);
}
