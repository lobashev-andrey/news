package com.example.news.web.controller.V1;

import com.example.news.mapper.NewsMapper;
import com.example.news.model.News;
import com.example.news.service.NewsService;
import com.example.news.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsResponseList> findAll(){
        return ResponseEntity.ok(
                newsMapper.newsListToResponseList(
                        newsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                newsMapper.newsToResponse(
                        newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> save(@RequestBody NewsRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newsMapper.newsToResponse(
                        newsService.save(
                                newsMapper.requestToNews(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody NewsRequest request){
        News updatedNews = newsService.update(newsMapper.requestToNews(id, request));
        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
