package com.example.news.service;

import com.example.news.exception.IllegalOperationException;
import com.example.news.filter.UserFilter;
import com.example.news.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll(UserFilter filter);

    User findById(Long id);

    User save(User user) throws IllegalOperationException;

    User update(User user) throws IllegalOperationException;

    void delete(Long id);
}
