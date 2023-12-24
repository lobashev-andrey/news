package com.example.news.repository;

import com.example.news.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatabaseRankRepository extends JpaRepository<Rank, Long>, JpaSpecificationExecutor<Rank> {

    Rank findByName(String name);
}
