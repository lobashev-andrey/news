package com.example.news.mapper.v2;

import com.example.news.model.Comment;
import com.example.news.web.dto.CommentRequest;
import com.example.news.web.dto.CommentResponse;
import com.example.news.web.dto.CommentResponseList;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapperV2 {

    default CommentResponse commentToCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getNews().getId(),
                comment.getUser().getId(),
                comment.getText()
        );

    }

    Comment commentRequestToComment(CommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment commentRequestToComment(Long commentId, CommentRequest request);

    List<CommentResponse> listToList(List<Comment> comments);

    default CommentResponseList commentListTocommentResponseList(List<Comment> comments) {
        return new CommentResponseList(
                listToList(comments));
    }
}
