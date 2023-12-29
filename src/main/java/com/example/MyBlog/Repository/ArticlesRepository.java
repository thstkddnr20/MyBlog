package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    List<Articles> findAllByMemberId(Long id);

}
