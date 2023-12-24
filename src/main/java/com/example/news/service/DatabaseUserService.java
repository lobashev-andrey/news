package com.example.news.service;

import com.example.news.filter.UserFilter;
import com.example.news.model.User;
import com.example.news.repository.DatabaseUserRepository;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseUserService implements UserService{

    private final DatabaseUserRepository repository;
    @Override
    public List<User> findAll(UserFilter filter) {

        return repository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize()))
                .getContent();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Пользователь с ID {0} не найден", id)));
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());

//        user.setNews(null);
        BeanUtils.nonNullPropertiesCopy(user, existedUser);

        repository.save(existedUser);

        return existedUser;
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
