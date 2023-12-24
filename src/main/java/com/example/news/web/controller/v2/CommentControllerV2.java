package com.example.news.web.controller.v2;

import com.example.news.aop.AuthorAccessCheck;
import com.example.news.filter.CommentFilter;
import com.example.news.mapper.v2.CommentMapperV2;
import com.example.news.service.DatabaseCommentService;
import com.example.news.service.DatabaseNewsService;
import com.example.news.web.dto.CommentRequest;
import com.example.news.web.dto.CommentResponse;
import com.example.news.web.dto.CommentResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/comment")
public class CommentControllerV2 {

    private final DatabaseCommentService service;
    private final CommentMapperV2 mapper;
    private DatabaseNewsService newsService;
    @Autowired
    public void setNewsService(DatabaseNewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<CommentResponseList> findAllByUserId(@Valid CommentFilter filter){
        newsService.findById(filter.getNewsId()); // Сообщит, если такой новости нет
        return ResponseEntity.ok(
                mapper.commentListTocommentResponseList(
                        service.findAllByNewsId(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                mapper.commentToCommentResponse(
                        service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.commentToCommentResponse(
                        service.save(
                                mapper.commentRequestToComment(request))));
    }

    @PutMapping("/{id}")
    @AuthorAccessCheck
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @RequestBody CommentRequest request){
        return ResponseEntity.ok(
                mapper.commentToCommentResponse(
                        service.update(
                                mapper.commentRequestToComment(id, request))));
    }

    @DeleteMapping("/{id}")
    @AuthorAccessCheck
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
