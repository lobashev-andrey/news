package com.example.news.repository;

import com.example.news.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    User save(User news);

    User update(User news);

    void delete(Long id);
}
