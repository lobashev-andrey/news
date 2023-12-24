package com.example.news.web.controller.v1;

import com.example.news.mapper.v1.NewsMapper;
import com.example.news.model.News;
import com.example.news.service.NewsService;
import com.example.news.web.dto.NewsRequest;
import com.example.news.web.dto.NewsResponse;
import com.example.news.web.dto.NewsResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsServiceImpl;
    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsResponseList> findAll(){
        return ResponseEntity.ok(
                newsMapper.newsListToResponseList(
                        newsServiceImpl.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                newsMapper.newsToResponse(
                        newsServiceImpl.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> save(@RequestBody NewsRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newsMapper.newsToResponse(
                        newsServiceImpl.save(
                                newsMapper.requestToNews(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody NewsRequest request){
        News updatedNews = newsServiceImpl.update(newsMapper.requestToNews(id, request));
        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
