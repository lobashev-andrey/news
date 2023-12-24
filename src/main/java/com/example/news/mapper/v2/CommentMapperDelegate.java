package com.example.news.mapper.v2;

import com.example.news.model.Comment;
import com.example.news.service.DatabaseNewsService;
import com.example.news.service.DatabaseUserService;
import com.example.news.web.dto.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class CommentMapperDelegate implements CommentMapperV2{


    private DatabaseUserService databaseUserService;
    private DatabaseNewsService databaseNewsService;
    @Autowired
    public void setDatabaseUserService(DatabaseUserService databaseUserService) {
        this.databaseUserService = databaseUserService;
    }

    @Autowired
    public void setDatabaseNewsService(DatabaseNewsService databaseNewsService) {
        this.databaseNewsService = databaseNewsService;
    }


    @Override
    public Comment commentRequestToComment(CommentRequest request) {
        Comment comment = new Comment();
        comment.setText(request.getText());

        comment.setNews(databaseNewsService.findById(request.getNewsId()));
        comment.setUser(databaseUserService.findById(request.getUserId()));

        return comment;
    }

    @Override
    public Comment commentRequestToComment(Long commentId, CommentRequest request){
        Comment comment = commentRequestToComment(request);
        comment.setId(commentId);
        return comment;

    }
}
