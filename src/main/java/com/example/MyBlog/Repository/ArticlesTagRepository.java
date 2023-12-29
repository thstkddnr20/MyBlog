package com.example.MyBlog.Repository;

import com.example.MyBlog.Domain.ArticlesTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesTagRepository extends JpaRepository<ArticlesTag, Long> {
}
