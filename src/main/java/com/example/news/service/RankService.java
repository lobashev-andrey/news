package com.example.news.service;

import com.example.news.exception.IllegalOperationException;
import com.example.news.filter.RankFilter;
import com.example.news.model.Rank;

import java.util.List;

public interface RankService {

    List<Rank> findAll(RankFilter filter);

    Rank findById(Long id);

    Rank save(Rank rank);

    Rank update(Rank rank);

    void delete(Long id) throws IllegalOperationException;
}
