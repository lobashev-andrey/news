package com.example.news.repository;

import com.example.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseNewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

//    @Override
//    @EntityGraph(attributePaths = "comments")
//    Optional<News> findById(Long id);
//
//    @Override
//    @EntityGraph(attributePaths = "comments")
//    Page<News> findAll(Specification<News> specification, Pageable pageable);


}
