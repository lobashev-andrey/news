package com.example.news.mapper.v2;

import com.example.news.mapper.v1.NewsMapper;
import com.example.news.model.User;
import com.example.news.web.dto.NewsResponseShort;
import com.example.news.web.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class UserMapperDelegate implements UserMapperV2{
    private NewsMapper newsMapper;
    //    private NewsMapperV2 newsMapper;
    @Autowired
    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public UserResponse userToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setId(user.getId());

        List<NewsResponseShort> news = user.getNews().stream()
                .map(newsMapper::newsToResponseShort).toList();
        response.setNews(news);

        return response;
    }



}
