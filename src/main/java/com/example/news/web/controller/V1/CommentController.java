package com.example.news.web.controller.V1;

import com.example.news.mapper.CommentMapper;
import com.example.news.service.CommentService;
import com.example.news.web.dto.CommentRequest;
import com.example.news.web.dto.CommentResponse;
import com.example.news.web.dto.CommentResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<CommentResponseList> findAll(){
        return ResponseEntity.ok(
                commentMapper.commentListTocommentResponseList(
                        commentService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                commentMapper.commentToCommentResponse(
                        commentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToCommentResponse(
                        commentService.save(
                                commentMapper.commentRequestToComment(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @RequestBody CommentRequest request){
        return ResponseEntity.ok(
                commentMapper.commentToCommentResponse(
                        commentService.update(
                                commentMapper.commentRequestToComment(id, request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
