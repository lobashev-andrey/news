package com.example.news.service;

import com.example.news.filter.RankFilter;
import com.example.news.model.Rank;
import com.example.news.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService{

    private final RankRepository repository;

    @Override
    public List<Rank> findAll(RankFilter filter) {
        return null; // Заглушка
    }

    public List<Rank> findAll() {
        return repository.findAll();
    }

    @Override
    public Rank findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Rank save(Rank rank) {
        return repository.save(rank);
    }

    @Override
    public Rank update(Rank rank) {
        return repository.update(rank);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
