package com.example.news.mapper.v1;

import com.example.news.model.User;
import com.example.news.web.dto.NewsResponseShort;
import com.example.news.web.dto.UserRequest;
import com.example.news.web.dto.UserResponse;
import com.example.news.web.dto.UserResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final NewsMapper newsMapper;

    public UserResponse userToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setId(user.getId());

        List<NewsResponseShort> news = user.getNews().stream()
                .map(newsMapper::newsToResponseShort).toList();
        response.setNews(news);

        return response;
    }

    public User userRequestToUser(UserRequest request){
        User user = new User();
        String name = request.getName();
        user.setName(name);

        return user;
    }

    public User userRequestToUser(Long id, UserRequest request){
        User user = userRequestToUser(request);
        user.setId(id);
        user.setNews(null); // чтобы .nonNullPropertiesCopy не перезаписывал пустым листом
        user.setComments(null); // чтобы .nonNullPropertiesCopy не перезаписывал пустым листом

        return user;
    }


    public UserResponseList userListToUserResponseList(List<User> users){
        UserResponseList response = new UserResponseList();
        List<UserResponse> responses = users.stream().map(this::userToUserResponse).toList();
        response.setUsers(responses);

        return response;
    }
}