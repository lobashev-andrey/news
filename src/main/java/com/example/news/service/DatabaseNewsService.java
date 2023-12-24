package com.example.news.service;

import com.example.news.filter.NewsFilter;
import com.example.news.model.News;
import com.example.news.repository.DatabaseNewsRepository;
import com.example.news.repository.NewsSpecification;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseNewsService implements NewsService{

private final DatabaseNewsRepository repository;

    @Override
    public List<News> findAll() {
        return repository.findAll();
    }

    @Override
    public News findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Новость с ID {0} не найдена", id)));
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public News update(News news) {
        News existedNews = findById(news.getId());

//        news.setComments(null);
        BeanUtils.nonNullPropertiesCopy(news, existedNews);

        repository.save(existedNews);
        return existedNews;
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public List<News> filterBy(NewsFilter filter) {

        return repository.findAll(
                NewsSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize()))
                .getContent();
    }
}
