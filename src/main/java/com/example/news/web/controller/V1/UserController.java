package com.example.news.web.controller.V1;

import com.example.news.mapper.UserMapper;
import com.example.news.model.User;
import com.example.news.service.UserService;
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

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserResponseList> findAll(){
        return ResponseEntity.ok(
                userMapper.userListToResponseList(
                        userService.findAll()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(
                        userService.save(
                                userMapper.requestToUser(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request){
        User updatedUser = userService.update(userMapper.requestToUser(id, request));
        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
