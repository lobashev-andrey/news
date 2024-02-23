package com.example.news.mapper.v2;

import com.example.news.model.RoleType;
import com.example.news.model.User;
import com.example.news.web.dto.UserRequest;
import com.example.news.web.dto.UserResponse;
import com.example.news.web.dto.UserResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapperV3 {

    private final NewsMapperV2 newsMapperV2;

    private final PasswordEncoder passwordEncoder;


    public User userRequestToUser(UserRequest request) {

        return User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(RoleType.valueOf("ROLE_" + request.getRole())))
                .build();
    }

    public User userRequestToUser(Long id, UserRequest request) {
        User user = userRequestToUser(request);
        user.setId(id);
//        user.setNews(null);
//        user.setComments(null);
        return user;
    }

    public UserResponse userToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setPassword(user.getPassword());
        response.setRoles(user.getRoles().stream().map(RoleType::name).toList());
        response.setNews(newsMapperV2.newsListToResponseList(user.getNews()).getNews());

        return response;
    }

    public UserResponseList userListToUserResponseList(List<User> users) {
        UserResponseList response = new UserResponseList();
        response.setUsers(users.stream().map(this::userToUserResponse).toList());

        return response;
    }
}
