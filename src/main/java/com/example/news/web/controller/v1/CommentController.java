package com.example.news.web.controller.v1;

import com.example.news.filter.CommentFilter;
import com.example.news.mapper.v1.CommentMapper;
import com.example.news.service.CommentService;
import com.example.news.web.dto.CommentRequest;
import com.example.news.web.dto.CommentResponse;
import com.example.news.web.dto.CommentResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentServiceImpl;
    private final CommentMapper commentMapperImpl;

    @GetMapping
    public ResponseEntity<CommentResponseList> findAll(@Valid CommentFilter filter){
        return ResponseEntity.ok(
                commentMapperImpl.commentListTocommentResponseList(
                        commentServiceImpl.findAllByNewsId(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                commentMapperImpl.commentToCommentResponse(
                        commentServiceImpl.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapperImpl.commentToCommentResponse(
                        commentServiceImpl.save(
                                commentMapperImpl.commentRequestToComment(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @RequestBody CommentRequest request){
        return ResponseEntity.ok(
                commentMapperImpl.commentToCommentResponse(
                        commentServiceImpl.update(
                                commentMapperImpl.commentRequestToComment(id, request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
