package com.example.news.repository;

import com.example.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatabaseUserRepository extends JpaRepository<User, Long>{

    Optional<User> findByName(String name);

//    @Override
//    @EntityGraph(attributePaths = {"news", "comments"})
//    Page<User> findAll(Pageable pageable);
//
//    @Override
//    @EntityGraph(attributePaths = {"news", "comments"})
//    Optional<User> findById(Long id);
}
