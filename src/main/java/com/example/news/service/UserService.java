package com.example.news.service;

import com.example.news.filter.UserFilter;
import com.example.news.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll(UserFilter filter);

    User findById(Long id);

    User save(User user);

    User update(User user);

    void delete(Long id);
}
