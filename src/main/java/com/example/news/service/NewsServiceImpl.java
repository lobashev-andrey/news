package com.example.news.service;

import com.example.news.filter.NewsFilter;
import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository repository;

    @Override
    public List<News> findAll() {
        return repository.findAll();
    }

    @Override
    public News findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public News update(News news) {
        return repository.update(news);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<News> filterBy(NewsFilter filter) {
        return null;
    }
}
