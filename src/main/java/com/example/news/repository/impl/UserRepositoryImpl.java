package com.example.news.repository.impl;

import com.example.news.model.User;
import com.example.news.repository.NewsRepository;
import com.example.news.repository.UserRepository;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(1);

    private final NewsRepository newsRepository;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream().filter(
                user -> user.getId().equals(id)).findFirst()
                        .orElseThrow(()-> new EntityNotFoundException(
                                MessageFormat.format("Пользователь с ID {0} не найден", id
                                )));
    }

    @Override
    public User save(User user) {
        user.setId(currentId.getAndIncrement());
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        Long id = user.getId();
        User existedUser = findById(id);
        BeanUtils.nonNullPropertiesCopy(user, existedUser);
        return existedUser;
    }

    @Override
    public void delete(Long id) {
        User existedUser = findById(id);
        newsRepository.deleteList(existedUser.getNews());

        users.remove(existedUser);
    }
}
