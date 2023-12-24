package com.example.news.web.controller.v2;

import com.example.news.filter.UserFilter;
import com.example.news.mapper.v2.UserMapperV2;
import com.example.news.service.DatabaseUserService;
import com.example.news.web.dto.UserRequest;
import com.example.news.web.dto.UserResponse;
import com.example.news.web.dto.UserResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/user")
public class UserControllerV2 {

    private final DatabaseUserService service;
    private final UserMapperV2 mapper;



    @GetMapping
    public ResponseEntity<UserResponseList> findAll(@Valid UserFilter filter){
        return ResponseEntity.ok(
                mapper.userListToUserResponseList(
                        service.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                mapper.userToUserResponse(
                        service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.userToUserResponse(
                        service.save(
                                mapper.userRequestToUser(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request){
        return ResponseEntity.ok(
                mapper.userToUserResponse(
                        service.update(
                                mapper.userRequestToUser(id, request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
