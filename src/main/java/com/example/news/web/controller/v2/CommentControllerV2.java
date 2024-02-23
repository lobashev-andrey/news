package com.example.news.web.controller.v2;

//import com.example.news.aop.AuthorAccessCheck;

import com.example.news.aop.annotation.CommentDeleteCheck;
import com.example.news.aop.annotation.CommentUpdateCheck;
import com.example.news.filter.CommentFilter;
import com.example.news.mapper.v2.CommentMapperV2;
import com.example.news.service.DatabaseCommentService;
import com.example.news.service.DatabaseNewsService;
import com.example.news.service.DatabaseUserService;
import com.example.news.web.dto.CommentRequest;
import com.example.news.web.dto.CommentResponse;
import com.example.news.web.dto.CommentResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/comment")
public class CommentControllerV2 {

    private final DatabaseCommentService service;
    private final CommentMapperV2 mapper;
    private final DatabaseUserService userService;
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
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request, @AuthenticationPrincipal UserDetails userDetails){

        request.setUserId(getUserId(userDetails));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.commentToCommentResponse(
                        service.save(
                                mapper.commentRequestToComment(request))));
    }

    @PutMapping("/{id}")
    @CommentUpdateCheck
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails, @RequestBody CommentRequest request){

        request.setUserId(getUserId(userDetails));

        return ResponseEntity.ok(
                mapper.commentToCommentResponse(
                        service.update(
                                mapper.commentRequestToComment(id, request))));
    }

    @DeleteMapping("/{id}")
    @CommentDeleteCheck
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public Long getUserId(UserDetails userDetails) {
        return userService.findByName(userDetails.getUsername()).getId();
    }

}
