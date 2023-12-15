package com.example.news.mapper;

import com.example.news.model.News;
import com.example.news.model.Rank;
import com.example.news.model.User;
import com.example.news.service.RankService;
import com.example.news.service.UserService;
import com.example.news.utils.BeanUtils;
import com.example.news.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsMapper {

    private final UserService userService;
    private final RankService rankService;
    private final CommentMapper commentMapper;

    public NewsResponse newsToResponse(News news){
        NewsResponse response = new NewsResponse();
        response.setId(news.getId());
        response.setName(news.getName());
        response.setText(news.getText());
        response.setUser_id(news.getUser().getId());
        response.setRank_id(news.getRank().getId());

        List<CommentResponse> responses = news.getComments().stream()
                .map(commentMapper::commentToCommentResponse).toList();
        response.setComments(responses);

        return response;
    }

    public NewsResponseShort newsToResponseShort(News news){
        NewsResponseShort response = new NewsResponseShort();
        response.setId(news.getId());
        response.setName(news.getName());
        response.setText(news.getText());
        response.setUser_id(news.getUser().getId());
        response.setRank_id(news.getRank().getId());
        response.setCommentsCount(news.getComments().size());
        return response;
    }

    public News requestToNews(NewsRequest request){
        News news = new News();
        news.setName(request.getName());
        news.setText(request.getText());

        User user = userService.findById(request.getUser_id());
        news.setUser(user);

        Rank rank = rankService.findById(request.getRank_id());
        news.setRank(rank);

        return news;
    }

    public News requestToNews(Long id, NewsRequest request){
        News news = requestToNews(request);
        news.setId(id);
        news.setComments(null);
        return news;
    }


    public NewsResponseList newsListToResponseList(List<News> newsList){
        NewsResponseList response = new NewsResponseList();
        List<NewsResponseShort> responses = newsList.stream().map(this::newsToResponseShort).toList();
        response.setNews(responses);
        return response;
    }
}
