//package com.example.news.service;
//
//import com.example.news.exception.EntityNotFoundException;
//import com.example.news.model.News;
//import com.example.news.repository.DatabaseNewsRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.MessageFormat;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DatabaseNewsService implements NewsService{
//
//private final DatabaseNewsRepository repository;
//
//    @Override
//    public List<News> findAll() {
//        return repository.findAll();
//    }
//
//    @Override
//    public News findById(Long id) throws EntityNotFoundException {
//        return repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
//                        "Новость с ID {0} не найдена", id
//                )));
//    }
//
//    @Override
//    public News save(News news) {
//        return repository.save(news);
//    }
//
//    @Override
//    public News update(News news) {
//        News existedNews = repository.findById(news.getId());
//        return null;
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//}
