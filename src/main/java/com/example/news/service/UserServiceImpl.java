package com.example.news.service;

import com.example.news.filter.UserFilter;
import com.example.news.model.User;
import com.example.news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    @Override
    public List<User> findAll(UserFilter filter) {
        return null; // Заглушка
    }

    public List<User> findAll() {
        return repository.findAll();
    }


    @Override
    public User findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
