package com.example.news.mapper.v1;

import com.example.news.model.Comment;
import com.example.news.model.News;
import com.example.news.model.User;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import com.example.news.web.dto.CommentRequest;
import com.example.news.web.dto.CommentResponse;
import com.example.news.web.dto.CommentResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final NewsService newsService;
    private final UserService userService;

    public CommentResponse commentToCommentResponse(Comment comment){
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setText(comment.getText());
        response.setUserId(comment.getUser().getId());
        response.setNewsId(comment.getNews().getId());

        return response;
    }

    public Comment commentRequestToComment(CommentRequest request){
        Comment comment = new Comment();
        comment.setText(request.getText());

        News news = newsService.findById(request.getNewsId());
        comment.setNews(news);

        User user = userService.findById(request.getUserId());
        comment.setUser(user);

        return comment;
    }

    public Comment commentRequestToComment(Long id, CommentRequest request){
        Comment comment = commentRequestToComment(request);
        comment.setId(id);

        return comment;
    }

   public CommentResponseList commentListTocommentResponseList(List<Comment> comments){
        CommentResponseList response = new CommentResponseList();
        List<CommentResponse> responses = comments.stream().map(this::commentToCommentResponse).toList();
        response.setComments(responses);

        return response;
    }
}
