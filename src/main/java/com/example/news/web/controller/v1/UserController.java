package com.example.news.web.controller.v1;

import com.example.news.mapper.v1.UserMapper;
import com.example.news.model.User;
import com.example.news.service.UserServiceImpl;
import com.example.news.web.dto.UserRequest;
import com.example.news.web.dto.UserResponse;
import com.example.news.web.dto.UserResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserResponseList> findAll(){
        return ResponseEntity.ok(
                userMapper.userListToUserResponseList(
                        userServiceImpl.findAll()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.userToUserResponse(
                        userServiceImpl.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToUserResponse(
                        userServiceImpl.save(
                                userMapper.userRequestToUser(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request){
        User updatedUser = userServiceImpl.update(userMapper.userRequestToUser(id, request));
        return ResponseEntity.ok(userMapper.userToUserResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
