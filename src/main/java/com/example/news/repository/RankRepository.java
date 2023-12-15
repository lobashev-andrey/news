package com.example.news.repository;

import com.example.news.model.Rank;

import java.util.List;

public interface RankRepository {

    List<Rank> findAll();

    Rank findById(Long id);

    Rank save(Rank rank);

    Rank update(Rank rank);

    void delete(Long id);

}
