package com.example.xssdemo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.xssdemo.model.Article;
import com.example.xssdemo.model.User;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
    Optional<Article> findById(UUID id);
    Optional<Article> findByIdAndUser(UUID id, User user);
    
    @Query(
        value = "SELECT p FROM Article p WHERE p.content LIKE %:q% OR p.title LIKE %:q%",
        countQuery = "SELECT COUNT(p) FROM Article p WHERE p.content LIKE %:q% OR p.title LIKE %:q%"
    )
    Page<Article> findByTitleOrContentContaining(@Param("q") String search, Pageable pageable);

    Page<Article> findByUserId(UUID userId, Pageable pageable);
}
