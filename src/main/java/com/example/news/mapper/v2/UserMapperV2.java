package com.example.news.mapper.v2;

import com.example.news.model.User;
import com.example.news.web.dto.UserRequest;
import com.example.news.web.dto.UserResponse;
import com.example.news.web.dto.UserResponseList;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(UserMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface UserMapperV2 {

    User userRequestToUser(UserRequest request);

    @Mapping(source = "id", target = "id")
    User userRequestToUser(Long id, UserRequest request);


    UserResponse userToUserResponse(User user);

    default UserResponseList userListToUserResponseList(List<User> users) {
        List<UserResponse> responses = users.stream().map(this::userToUserResponse).toList();

        return new UserResponseList(responses);
    }
}
