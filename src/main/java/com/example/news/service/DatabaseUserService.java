package com.example.news.service;

import com.example.news.exception.IllegalOperationException;
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

    public User findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Пользователь с именем {0} не найден", name)));
    }


    @Override
    public User save(User user) throws IllegalOperationException {
        usernameFreeCheck(user.getName());

        return repository.save(user);
    }

    @Override
    public User update(User user) throws IllegalOperationException {
        User existedUser = findById(user.getId());

        if ( !user.getName().equals(existedUser.getName()) ) {
            usernameFreeCheck(user.getName());
        }

        BeanUtils.nonNullPropertiesCopy(user, existedUser);

        repository.save(existedUser);

        return existedUser;
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }


    public void usernameFreeCheck(String username) throws IllegalOperationException {
        if (repository.findByName(username).isPresent()) {
            throw new IllegalOperationException("Этот юзернейм занят, используйте другой вариант");
        }
    }
}
