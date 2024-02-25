package com.example.news.web.controller.v2;

import com.example.news.aop.annotation.UserDataAccessCheck;
import com.example.news.exception.IllegalOperationException;
import com.example.news.exception.UnauthorizedAccessException;
import com.example.news.filter.UserFilter;
import com.example.news.mapper.v2.UserMapperV3;
import com.example.news.service.DatabaseUserService;
import com.example.news.web.dto.UserRequest;
import com.example.news.web.dto.UserResponse;
import com.example.news.web.dto.UserResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/user")
public class UserControllerV2 {

    private final DatabaseUserService service;
    private final UserMapperV3 mapper;


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseList> findAll(@Valid UserFilter filter){
        return ResponseEntity.ok(
                mapper.userListToUserResponseList(
                        service.findAll(filter)));
    }

    @GetMapping("/{id}")
    @UserDataAccessCheck
    public ResponseEntity<UserResponse> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) throws UnauthorizedAccessException {
        return ResponseEntity.ok(
                mapper.userToUserResponse(
                        service.findById(id)));
    }

    @PutMapping("/{id}")
    @UserDataAccessCheck
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody UserRequest request)
            throws UnauthorizedAccessException, IllegalOperationException {
        return ResponseEntity.ok(
                mapper.userToUserResponse(
                        service.update(
                                mapper.userRequestToUser(id, request))));
    }

    @DeleteMapping("/{id}")
    @UserDataAccessCheck
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) throws UnauthorizedAccessException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
