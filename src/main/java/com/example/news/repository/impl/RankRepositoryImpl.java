package com.example.news.repository.impl;

import com.example.news.model.Rank;
import com.example.news.repository.RankRepository;
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
public class RankRepositoryImpl implements RankRepository {


    private final List<Rank> ranks = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(1);


    @Override
    public List<Rank> findAll() {
        return ranks;
    }

    @Override
    public Rank findById(Long id) {
        return ranks.stream().filter(
                        rank -> rank.getId().equals(id)).findFirst()
                .orElseThrow(()-> new EntityNotFoundException(
                        MessageFormat.format("Категория с ID {0} не найдена", id
                        )));
    }

    @Override
    public Rank save(Rank rank) {
        rank.setId(currentId.getAndIncrement());
        ranks.add(rank);
        return rank;
    }

    @Override
    public Rank update(Rank rank) {
        Long id = rank.getId();
        Rank existedRank = findById(id);
        BeanUtils.nonNullPropertiesCopy(rank, existedRank);
        return existedRank;
    }

    @Override
    public void delete(Long id) {
        Rank existedRank = findById(id);
        // Удалять новости из-за удаления категории вряд ли стоит
        // Но оставим местечко для возможной добавки.
        ranks.remove(existedRank);
    }
}
