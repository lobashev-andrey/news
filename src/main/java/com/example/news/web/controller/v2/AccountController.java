package com.example.news.web.controller.v2;

import com.example.news.mapper.v2.UserMapperV3;
import com.example.news.service.DatabaseUserService;
import com.example.news.web.dto.UserRequest;
import com.example.news.web.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/account")
public class AccountController {

    private final DatabaseUserService service;
    private final UserMapperV3 mapper;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.userToUserResponse(
                        service.save(
                                mapper.userRequestToUser(request))));
    }
}
