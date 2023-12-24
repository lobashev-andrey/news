package com.example.news.repository;

import com.example.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseUserRepository extends JpaRepository<User, Long> {
}
