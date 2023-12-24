package com.example.news.service;

import com.example.news.exception.IllegalOperationException;
import com.example.news.filter.RankFilter;
import com.example.news.model.News;
import com.example.news.model.Rank;
import com.example.news.repository.DatabaseRankRepository;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseRankService implements RankService{

    private final DatabaseRankRepository repository;
    @Override
    public List<Rank> findAll(RankFilter filter) {

        return repository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public Rank findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Категория с ID {0} не найдена", id)
                ));
    }

    @Override
    public Rank save(Rank rank) {
        return repository.save(rank);
    }

    @Override
    public Rank update(Rank rank) {
        Rank existedRank = findById(rank.getId());

//        rank.setNews(null);
        BeanUtils.nonNullPropertiesCopy(rank, existedRank);

        repository.save(existedRank);

        return existedRank;
    }

    @Override
    public void delete(Long id) throws IllegalOperationException {
        Rank noThemeRank = getNoThemeRank();

        if(Objects.equals(noThemeRank.getId(), id)){
            throw new IllegalOperationException(MessageFormat.format(
                    "Категория «Без темы» (ID {0}) не может быть удалена", id));
        }

        Rank rankToDelete = findById(id);
        for(News news : rankToDelete.getNews()){
            news.setRank(noThemeRank);
            noThemeRank.getNews().add(news);
        }

        repository.delete(rankToDelete);
    }

    public Rank getNoThemeRank(){
        Rank noThemeRank = repository.findByName("Без темы");

        if(noThemeRank == null){
            Rank rank = new Rank();
            rank.setName("Без темы");
            return save(rank);
        }

        return noThemeRank;
    }



}
